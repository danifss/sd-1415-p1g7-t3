package CustomerSide;

import Interfaces.RepositoryInterface;
import Interfaces.ShopInterface;
import java.rmi.RemoteException;

/**
 * This class is responsible to host the Customers
 *
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 3.0
 */
public class Customer extends Thread implements CustomerInterface {

    /**
     * Customer States
     */
    private final static int 
            CARRYING_OUT_DAILY_CHORES = 0,
            CHECKING_DOOR_OPEN = 1,
            APPRAISING_OFFER_IN_DISPLAY = 2,
            BUYING_SOME_GOODS = 3;

    /**
     * Customer identity
     *
     * @serial customerId
     */
    private final int customerId;

    /**
     * Present Customer State
     *
     * @serial customerState
     */
    private int customerState;

    /**
     * Shop
     *
     * @serial shop
     */
    private ShopInterface shop;

    /**
     * General Repository
     *
     * @serial sharedInfo
     */
    private RepositoryInterface sharedInfo;

    /**
     * Total number of goods bought buy the customer
     *
     * @serial nGoodsBought
     */
    private int nGoodsBought;

    /**
     * Number of products that the customer has with him
     *
     * @serial nProductsCustomerHas
     */
    private int nProductsCustomer;
    
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
     * Index of the first Customer in the clock Array
     * @serialField first_v Index
     */
    private int first_v;

    /**
     * Create customer thread
     *
     * @param sharedInfo General repository
     * @param customerId customer identification
     * @param shop Shop
     * @param nCustomers Number of Customers (Info to create clock array)
     * @param nCraftmans Number of Craftmans (Info to create clock array)
     */
    public Customer(int customerId, RepositoryInterface sharedInfo, ShopInterface shop, int nCustomers, int nCraftmans){
        this.sharedInfo = sharedInfo;
        this.customerId = customerId;
        this.shop = shop;
        this.customerState = CARRYING_OUT_DAILY_CHORES;
        this.nGoodsBought = 0;
        
        // Clock
        num_v = 1 + nCustomers + nCraftmans;
        v = new int[num_v];
        for(int i = 0; i < num_v; i++){
            v[i] = 0;
        }
        first_v = 1;
    }

    /**
     * Life cycle of the customer.
     */
    @Override
    public void run(){
        System.out.println("Iniciado o Customer: " + customerId);
        
        try {
            while (!endOper()){
                switch (customerState){
                    case CARRYING_OUT_DAILY_CHORES:
                        livingNormalLife();
                        goShopping();
                        break;
                    case CHECKING_DOOR_OPEN:
                        if (isDoorOpen()){
                            enterShop();
                            System.out.printf("Cliente %d\t- Entrou na loja.\n", customerId);
                        } else{
                            tryAgainLater();
                        }
                        break;
                    case APPRAISING_OFFER_IN_DISPLAY:
                        perusingAround();
                        System.out.printf("Cliente %d\t- A escolher produtos.\n", customerId);
                        if (nProductsCustomer > 0){
                            iWantThis();
                            System.out.printf("Cliente %d\t- Comprou %d produtos.\n", customerId, nProductsCustomer);
                        } else{
                            exitShop();
                            System.out.printf("Cliente %d\t- Saiu sem comprar produtos.\n", customerId);
                        }
                        break;
                    case BUYING_SOME_GOODS:
                        exitShop();
                        System.out.printf("Cliente %d\t- Saiu da loja.\n", customerId);
                        break;
                }
            }
        } catch(RemoteException e) {
            System.out.println("Customer life cycle exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Terminado o Customer: " + customerId);
    }

    /**
     * Living normal life.
     */
    private void livingNormalLife(){
        v[first_v+customerId]++;
        try{
            sleep((long) (1000 + 200 * Math.random()));
        } catch (InterruptedException e){
        }
    }

    /**
     * Customer goes shopping. First, he will need to check if the door is open.
     */
    private void goShopping() throws RemoteException{
        v[first_v+customerId]++;
        try{
            sleep((long) (20));
        } catch (InterruptedException e){
        }
        setCustomerState(CHECKING_DOOR_OPEN);
    }

    /**
     * Customer checks if the shop is open.
     */
    private boolean isDoorOpen() throws RemoteException{
        try{
            sleep((long) (20));
        } catch (InterruptedException e){
        }
        return shop.isDoorOpen();
    }

    /**
     * If the shop is not open, he will try again later.
     */
    private void tryAgainLater() throws RemoteException{
        v[first_v+customerId]++;
        try{
            sleep((long) (20));
        } catch (InterruptedException e){
        }
        setCustomerState(CARRYING_OUT_DAILY_CHORES);

        try{
            sleep((long) (750 + 200 * Math.random()));
        } catch (InterruptedException e){
        }
    }

    /**
     * Customer enters the shop.
     */
    private void enterShop() throws RemoteException{
        v[first_v+customerId]++;
        try{
            sleep((long) (20));
        } catch (InterruptedException e){
        }
        shop.enterShop();
        setCustomerState(APPRAISING_OFFER_IN_DISPLAY);
    }

    /**
     * Customer chooses if he wants to buy something. If he wants, he collects some products.
     *
     * @return Number of goods to buy
     */
    private void perusingAround() throws RemoteException{
        v[first_v+customerId]++;
        try{
            sleep((long) (100 + 20 * Math.random()));
        } catch (InterruptedException e){
        }

        nProductsCustomer = shop.perusingAround();
    }

    /**
     * Customer goes to the queue to buy the products. He will wait until is his turn in the queue.
     *
     * @param goods
     */
    private void iWantThis() throws RemoteException{
        v[first_v+customerId]++;
        try{
            sleep((long) (20));
        } catch (InterruptedException e){
        }
        setCustomerState(BUYING_SOME_GOODS);

        shop.iWantThis(customerId, nProductsCustomer, v);
        int[] temp_v = shop.getClockOwner();
        v[first_v+customerId]++;
        for(int i = 0; i < num_v; i++){
            if(temp_v[i] > v[i]){
                v[i] = temp_v[i];
            }
        }
        nGoodsBought += nProductsCustomer;
        sharedInfo.setnGoodsByCustomer(customerId, nGoodsBought);
    }

    /**
     * Customer leaves the Shop.
     */
    private void exitShop() throws RemoteException{
        v[first_v+customerId]++;
        try{
            sleep((long) (100 + 25 * Math.random()));
        } catch (InterruptedException e){
        }
        shop.exitShop(v);
        setCustomerState(CARRYING_OUT_DAILY_CHORES);
    }

    /**
     * Verifies if the Customer can stop working. From the shop, he checks if all products have been
     * transferred to Shop, and all the products are sold. He needs to be in the state
     * CARRYING_OUT_DAILY_CHORES
     *
     * @return true if needs to stop
     */
    private boolean endOper() throws RemoteException{
        return shop.endOper() && customerState == CARRYING_OUT_DAILY_CHORES;
    }

    /**
     * Change the present Customer state.
     *
     * @param customerState
     */
    private void setCustomerState(int customerState) throws RemoteException{
        this.customerState = customerState;
        sharedInfo.setCustomerState(customerId, customerState, v);
    }
}
