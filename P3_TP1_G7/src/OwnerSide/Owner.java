package OwnerSide;

import Interfaces.RepositoryInterface;
import Interfaces.ShopInterface;
import Interfaces.FactoryInterface;
import Interfaces.StorageInterface;
import java.rmi.RemoteException;

/**
 * This class is responsible to host the Owner
 *
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 3.0
 */
public class Owner extends Thread implements OwnerInterface {

    /**
     * Owner States
     */
    private final static int 
            OPENING_THE_SHOP = 0,
            WAITING_FOR_NEXT_TASK = 1,
            ATTENDING_A_CUSTOMER = 2,
            CLOSING_THE_SHOP = 3,
            DELIVERING_PRIME_MATERIALS = 4,
            COLLECTING_A_BATCH_OF_PRODUCTS = 5,
            BUYING_PRIME_MATERIALS = 6;

    /**
     * Decision taken in AppraiseSit
     */
    private final static int 
            NEED_TO_CLOSE_SHOP = 0,
            ADDRESS_CUSTOMER = 1;

    /**
     * General Repository
     *
     * @serial sharedInfo
     */
    private RepositoryInterface sharedInfo;

    /**
     * Factory
     *
     * @serial factory
     */
    private FactoryInterface factory;

    /**
     * Shop
     *
     * @serial shop
     */
    private ShopInterface shop;

    /**
     * Storage
     *
     * @serial storage
     */
    private StorageInterface storage;

    /**
     * Present State of the Owner
     *
     * @serial ownerState
     */
    private int ownerState;

    /**
     * Number of prime materials that the owner has
     *
     * @serial nPrimeMaterials
     */
    private int nPrimeMaterials;

    /**
     * Id of the customer that the owner is attending
     *
     * @serial attendingCustomerId
     */
    private int attendingCustomerId;
    
    /**
     * Array with local clock with (1+nCustomers+nCraftmans) size:
     * i = 0 -> Owner
     * i = 1 to nCustomers -> Customers
     * i = nCustomers+1 to nCustomers+nCraftmans -> Craftmans
     * @serial v Local clock
     */
    private int[] v;
    
    /**
     * Number of elements in the clock array.
     * @serialField num_v Number of elements
     */
    private int num_v;

    /**
     * Create owner thread.
     *
     * @param sharedInfo General repository
     * @param factory Factory
     * @param shop Shop
     * @param storage Storage
     * @param nCustomers Number of Customers (Info to create clock array)
     * @param nCraftmans Number of Craftmans (Info to create clock array)
     */
    public Owner(RepositoryInterface sharedInfo, FactoryInterface factory, ShopInterface shop, StorageInterface storage, int nCustomers, int nCraftmans){
        this.sharedInfo = sharedInfo;
        this.factory = factory;
        this.shop = shop;
        this.storage = storage;
        ownerState = OPENING_THE_SHOP;
        attendingCustomerId = -1;
        
        // Clock
        num_v = 1 + nCustomers + nCraftmans;
        v = new int[num_v];
        for(int i = 0; i < num_v; i++){
            v[i] = 0;
        }
    }

    /**
     * Life cycle of the Owner.
     */
    @Override
    public void run() {
        System.out.println("Iniciado o Owner.");

        try {
            shop.openTheDoor(); // Owner needs to open the shop before he starts working

            while (!endOper()){
                switch (ownerState){
                    case OPENING_THE_SHOP:
                        prepareToWork();
                        break;
                    case WAITING_FOR_NEXT_TASK:
                        int decision = appraiseSit();
                        if (decision == NEED_TO_CLOSE_SHOP){
                            closeTheDoor();
                            if (customersInTheShop()){
                                break;
                            }
                            prepareToLeave();
                        } else if (decision == ADDRESS_CUSTOMER){
                            addressACustomer();
                        }
                        break;
                    case ATTENDING_A_CUSTOMER:
                        System.out.printf("Owner\t\t- Atender o cliente %d.\n", attendingCustomerId);
                        serviceCustomer();
                        sayGoodByeToCustomer();
                        break;
                    case CLOSING_THE_SHOP:
                        if (shop.isSupplyMaterialsToFactory()){
                            visitSuppliers();
                            System.out.printf("Owner\t\t- Vou ao armazem comprar materia prima.\n");
                        } else if (shop.isTranfsProductsToShop()){
                            goToWorkShop();
                            System.out.printf("Owner\t\t- Vou a oficina.\n");
                        } else{
                            System.out.printf("Owner\t\t- Fim.\n");
                            returnToShop();
                        }
                        break;
                    case COLLECTING_A_BATCH_OF_PRODUCTS:
                        returnToShop();
                        break;
                    case BUYING_PRIME_MATERIALS:
                        if (nPrimeMaterials > 0){
                            replenishStock();
                            System.out.printf("Owner\t\t- Repor stock da oficina.\n");
                        } else{
                            setOwnerState(DELIVERING_PRIME_MATERIALS);
                        }
                        break;
                    case DELIVERING_PRIME_MATERIALS:
                        returnToShop();
                        break;
                }
            }
        } catch(RemoteException e){
            System.out.println("Owner life cycle exception" + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Terminado o Owner.");
    }

    /**
     * Owner prepares to work, changing his state to waiting for the next task.
     */
    private void prepareToWork() throws RemoteException{
        v[0]++;
        try{
            sleep((long) (20));
        } catch (InterruptedException e){
        }
        setOwnerState(WAITING_FOR_NEXT_TASK);
    }

    /**
     * Owner waits to see what he needs to do.
     *
     * @return action he will do
     */
    private int appraiseSit() throws RemoteException{
        v[0]++;
        try{
            sleep((long) (20));
        } catch (InterruptedException e){
        }
        int temp = shop.appraiseSit();
        if(temp == NEED_TO_CLOSE_SHOP){
            int[] temp_v = shop.getClockCraftman();
            boolean v_changed = false;
            for(int i = 0; i < num_v; i++){
                if(temp_v[i] > v[i]){
                    v_changed = true;
                    v[i] = temp_v[i];
                }
            }
            if(v_changed){
                v[0]++;
            }
        }else if(temp == ADDRESS_CUSTOMER){
            int[] temp_v = shop.getClockCustomer();
            boolean v_changed = false;
            for(int i = 0; i < num_v; i++){
                if(temp_v[i] > v[i]){
                    v_changed = true;
                    v[i] = temp_v[i];
                }
            }
            if(v_changed){
                v[0]++;
            }
        }
        return temp;
    }

    /**
     * Owner closes the door of the Shop.
     */
    private void closeTheDoor() throws RemoteException{
        v[0]++;
        try{
            sleep((long) (20));
        } catch (InterruptedException e){
        }
        shop.closeTheDoor();
    }

    /**
     * Owner sees if there is customers in the shop.
     */
    private boolean customersInTheShop() throws RemoteException{
        try{
            sleep((long) (20));
        } catch (InterruptedException e){
        }
        return shop.customersInTheShop();
    }

    /**
     * Owner prepares to go to the Factory.
     */
    private void prepareToLeave() throws RemoteException{
        v[0]++;
        try{
            sleep((long) (20));
        } catch (InterruptedException e){
        }
        setOwnerState(CLOSING_THE_SHOP);
    }

    /**
     * Owner prepares to address a customer.
     */
    private void addressACustomer() throws RemoteException{
        v[0]++;
        try{
            sleep((long) (100));
        } catch (InterruptedException e){
        }
        setOwnerState(ATTENDING_A_CUSTOMER);
        attendingCustomerId = shop.addressACustomer(v); // atende cliente seguinte
    }

    /**
     * Owner services a Customer. He will take more time if the Customer is buying more products.
     */
    private void serviceCustomer() throws RemoteException{
        v[0]++;
        try{
            sleep((long) (20));
        } catch (InterruptedException e){
        }
        int n = shop.serviceCustomer();
        int[] temp_v = shop.getClockCustomer(attendingCustomerId);
        v[0]++;
        for(int i = 0; i < num_v; i++){
            if(temp_v[i] > v[i]){
                v[i] = temp_v[i];
            }
        }
        
        int i = 0;
        while (i < n){
            //Debug
            System.out.println("--------Tratando produto: " + (i + 1) + " do customer: " + attendingCustomerId);
            try{
                sleep((long) (25 + 10 * Math.random()));
            } catch (InterruptedException e){
            }
            i++;
        }
    }

    /**
     * Owner finish the purchase and say goodbye to the Customer. Then he wait for the next task.
     */
    private void sayGoodByeToCustomer() throws RemoteException{
        v[0]++;
        try{
            sleep((long) (20));
        } catch (InterruptedException e){
        }
        shop.sayGoodByeToCustomer(v);
        if (shop.isShopStillOpen()){
            closeTheDoor();
        }
        setOwnerState(WAITING_FOR_NEXT_TASK);
    }

    /**
     * Owner goes to Factory to collect products. Then he adds the products collected to the
     * display.
     */
    private void goToWorkShop() throws RemoteException{
        v[0]++;
        try{
            sleep((long) (20));
        } catch (InterruptedException e){
        }
        setOwnerState(COLLECTING_A_BATCH_OF_PRODUCTS);

        shop.goToWorkshop();
        int products = factory.goToWorkshop();

        shop.addnGoodsInDisplay(products);
    }

    /**
     * Owner goes to the storage to collect some prime materials.
     */
    private void visitSuppliers() throws RemoteException {
        v[0]++;
        try{
            sleep((long) (20));
        } catch (InterruptedException e){
        }
        setOwnerState(BUYING_PRIME_MATERIALS);

        try {
            if (storage.isPrimeMaterialsAvailabe()){
                nPrimeMaterials = storage.visitSuppliers();
            } else{
                nPrimeMaterials = 0;
            }
        } catch(RemoteException e) {
            System.out.println("RMI Owner visitSuppliers exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Owner returns to the shop and opens the door.
     */
    private void returnToShop() throws RemoteException{
        v[0]++;
        try{
            sleep((long) (20));
        } catch (InterruptedException e){
        }

        setOwnerState(OPENING_THE_SHOP);
        shop.openTheDoor();
    }

    /**
     * Owner delivers prime materials to the Factory.
     */
    private void replenishStock() throws RemoteException{
        v[0]++;
        try{
            sleep((long) (20));
        } catch (InterruptedException e){
        }
        setOwnerState(DELIVERING_PRIME_MATERIALS);
        shop.replenishStock();
        factory.replenishStock(nPrimeMaterials, v);
        nPrimeMaterials = 0;
    }

    /**
     * Verifies if the Owner can stop working. From the shop, he checks if all products have been
     * transferred to Shop, and all the products are sold. He needs to be in the State
     * CLOSING_THE_SHOP and the shop can't have Customers inside.
     *
     * @return true if needs to stop
     */
    private boolean endOper() throws RemoteException{
        return shop.endOper() && (ownerState == CLOSING_THE_SHOP) && !shop.customersInTheShop();
    }

    /**
     * Change the owner state.
     *
     * @param ownerState state of the owner
     */
    private void setOwnerState(int ownerState) throws RemoteException{
        this.ownerState = ownerState;
        sharedInfo.setOwnerState(ownerState, v);
    }
}
