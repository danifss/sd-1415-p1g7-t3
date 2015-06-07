package ShopSide;

import Interfaces.RepositoryInterface;
import Interfaces.ShopInterface;
import Registry.Configurations;
import java.rmi.RemoteException;

/**
 * This class is responsible to host the Shop
 *
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 3.0
 */
public class Shop implements ShopInterface {

    /**
     * Shop States.
     */
    private final static int 
            CLOSED = 0,
            STILL_OPEN = 1,
            OPEN = 2;

    /**
     * Decision taken in AppraiseSit.
     */
    private final static int 
            NEED_TO_CLOSE_SHOP = 0,
            ADDRESS_CUSTOMER = 1;

    /**
     * Present State of Shop.
     *
     * @serial shopState
     */
    private int shopState;

    /**
     * General Repository.
     *
     * @serial sharedInfo
     */
    private final RepositoryInterface sharedInfo;

    /**
     * FIFO with Customers waiting for their turn to buy something.
     *
     * @serial queueCustomer
     */
    private MemFIFO queueCustomer;

    /**
     * Number of customers inside the shop.
     *
     * @serial customerInsideShop
     */
    private int customerInsideShop;

    /**
     * Number of goods that the shop has to sell.
     *
     * @serial nGoodsInDisplay
     */
    private int nGoodsInDisplay;

    /**
     * Id of the customer that the owner is attending.
     *
     * @serial attendingCustomerId;
     */
    private int attendingCustomerId;

    /**
     * Number of goods that the customer is buying.
     *
     * @serial nGoodsCustomerHave
     */
    private int nGoodsCustomerHave;

    /**
     * Flag to see if the owner already finished the purchase.
     *
     * @serial flagPurchaseMade
     */
    private boolean flagPurchaseMade;

    /**
     * Flag to see if the Factory has products to bring.
     *
     * @serial flagBringProductsFromFactory
     */
    private int flagBringProductsFromFactory;

    /**
     * Flag to see if the Factory needs prime materials.
     *
     * @serial flagPrimeMaterialsNeeded
     */
    private boolean flagPrimeMaterialsNeeded;

    /**
     * Total amount of products delivered.
     *
     * @serial nProductsDelivered
     */
    private int nProductsDelivered;

    /**
     * Total number of products that the shop can have in this experience.
     *
     * @serial totalProducts
     */
    private final int totalProducts;
    
    /**
     * Array with local clock with (1+nCustomers+nCraftmans) size from the Craftmans:
     * i = 0 -> Owner
     * i = 1 to nCustomers -> Customers
     * i = nCustomers+1 to nCustomers+nCraftmans -> Craftmans
     * @serial v_craftman Local clock
     */
    private int[] v_craftman;
    
    /**
     * Array with local clock with (1+nCustomers+nCraftmans) size from the Owner:
     * i = 0 -> Owner
     * i = 1 to nCustomers -> Customers
     * i = nCustomers+1 to nCustomers+nCraftmans -> Craftmans
     * @serial v_owner Local clock
     */
    private int[] v_owner;
    
    /**
     * Array with local clock with (1+nCustomers+nCraftmans) size from the Customer:
     * i = 0 -> Owner
     * i = 1 to nCustomers -> Customers
     * i = nCustomers+1 to nCustomers+nCraftmans -> Craftmans
     * @serial v_customer Local clock
     */
    private int[] v_customer;
    
    /**
     * Array of customers with an array with local clock with (1+nCustomers+nCraftmans) size:
     * i = 0 -> Owner
     * i = 1 to nCustomers -> Customers
     * i = nCustomers+1 to nCustomers+nCraftmans -> Craftmans
     * Stores values from different customers because of the queue.
     * @serial v_customer_queue Local clock
     */
    private int[][] v_customer_queue;
    
    /**
     * Number of elements in the clock array.
     * @serialField num_v Number of elements
     */
    private int num_v;

    /**
     * Create Monitor of the Shop.
     *
     * @param nInitialProductsInShop Initial number of products in the shop at the beginning
     * @param nCustomer Number of customers (Also used as info to create clock array)
     * @param sharedInfo General repository
     * @param totalProducts Total number of products that the shop can have in this experience
     * @param nCraftmans Number of Craftmans (Info to create clock array)
     */
    public Shop(int nInitialProductsInShop, int nCustomer, int totalProducts, RepositoryInterface sharedInfo, int nCraftmans){
        this.sharedInfo = sharedInfo;
        shopState = CLOSED;
        customerInsideShop = 0;
        queueCustomer = new MemFIFO(nCustomer);
        nGoodsInDisplay = nInitialProductsInShop;
        nGoodsCustomerHave = 0;
        attendingCustomerId = -1;
        flagBringProductsFromFactory = 0;
        flagPrimeMaterialsNeeded = false;
        flagPurchaseMade = false;
        nProductsDelivered = nInitialProductsInShop;
        this.totalProducts = totalProducts;
        
        // Clock
        num_v = 1 + nCustomer + nCraftmans;
        v_craftman = new int[num_v];
        v_owner = new int[num_v];
        v_customer = new int[num_v];
        v_customer_queue = new int[nCustomer][num_v];
        for(int i = 0; i < num_v; i++){
            v_craftman[i] = 0;
            v_owner[i] = 0;
            v_customer[i] = 0;
        }
        for(int i = 0; i < nCustomer; i++){
            for(int j = 0; j < num_v; j++){
                v_customer_queue[i][j] = 0;
            }
        }
    }

    /**
     * See if there is customers inside the shop.
     *
     * @return true if the number of Customers inside the shop is greater than 0
     */
    @Override
    public boolean customersInTheShop(){
        return customerInsideShop > 0;
    }

    /**
     * See if the Queue has customers.
     *
     * @return true if the queue has customers
     */
    @Override
    public boolean customerInTheQueue(){
        return !this.queueCustomer.isEmpty();
    }

    /**
     * Owner sees the situation of the shop and decide what to do. If the shop is open, he waits
     * until a Customer or a Craftman calls him. If the shop is still open, he wait until all
     * Customers leave the shop, and then proceeds to the request from the Factory.
     *
     * @return action to do
     */
    @Override
    public synchronized int appraiseSit(){
        if (!endOper() || customersInTheShop()){
            if (shopState == OPEN){
                try{
                    while (queueCustomer.isEmpty() && !isSupplyMaterialsToFactory() && !isTranfsProductsToShop() && !endOper()){
                        wait();
                        Thread.sleep(1000);
                    }
                } catch (Exception e){
                }

                if (isSupplyMaterialsToFactory() || isTranfsProductsToShop() || endOper()){
                    return NEED_TO_CLOSE_SHOP;
                }
                return ADDRESS_CUSTOMER;
            }
            try{
                while (queueCustomer.isEmpty() && customersInTheShop()){
                    wait();
                    Thread.sleep(1000);
                }
            } catch (Exception e){
            }
            if (!customersInTheShop()){
                return NEED_TO_CLOSE_SHOP;
            }
            return ADDRESS_CUSTOMER;
        }
        return NEED_TO_CLOSE_SHOP;
    }

    /**
     * Owner closes the door. If the shop has Customers inside, the shop change his state to
     * STILL_OPEN, if the shop doesn't have Customers, the shop change his state to CLOSED.
     */
    @Override
    public synchronized void closeTheDoor() throws RemoteException{
        if (customersInTheShop()){
            setShopState(STILL_OPEN);
            System.out.println("Shop\t\t- Vai Fechar.");
        } else{
            setShopState(CLOSED);
            System.out.println("Shop\t\t- Porta Fechada.");
        }
    }

    /**
     * Owner opens the door. The state of the Shop is changed to OPEN.
     */
    @Override
    public synchronized void openTheDoor() throws RemoteException{
        setShopState(OPEN);
        System.out.println("Shop\t\t- Porta Aberta.");
    }

    /**
     * See if the shop is on state STILL_OPEN.
     *
     * @return true if the shop is STILL_OPEN
     */
    @Override
    public synchronized boolean isShopStillOpen(){
        return shopState == STILL_OPEN;
    }

    /**
     * The owner goes to the Factory to collect products.
     */
    @Override
    public synchronized void goToWorkshop() throws RemoteException{
        flagBringProductsFromFactory -= 1;
        if (flagBringProductsFromFactory > 0){
            sharedInfo.setTranfsProductsToShop(true);
        } else{
            sharedInfo.setTranfsProductsToShop(false);
        }
    }

    /**
     * Update the number of products that the shop is selling.
     *
     * @param goods Number of products to add to the number of products in display
     */
    @Override
    public synchronized void addnGoodsInDisplay(int goods) throws RemoteException{
        nGoodsInDisplay += goods;
        nProductsDelivered += goods;
        sharedInfo.setnGoodsInDisplay(nGoodsInDisplay);
    }

    /**
     * Owner goes to Factory to restock prime materials.
     */
    @Override
    public synchronized void replenishStock() throws RemoteException{
        flagPrimeMaterialsNeeded = false;
        sharedInfo.setSupplyMaterialsToFactory(flagPrimeMaterialsNeeded);
    }

    /**
     * The Craftman indicates that prime materials is needed in the Factory. He changes the flag and
     * wakes up the Owner.
     * @param v Last clock
     */
    @Override
    public synchronized void primeMaterialsNeeded(int[] v) throws RemoteException{
        flagPrimeMaterialsNeeded = true;
        sharedInfo.setSupplyMaterialsToFactory(flagPrimeMaterialsNeeded);
        for(int i = 0; i < num_v; i++){
            if(v[i] > v_craftman[i]){
                v_craftman[i] = v[i];
            }
        }
        notifyAll();
    }

    /**
     * The Craftman indicates that the Owner can go to factory to collect products. He changes the
     * flag and wakes up the Owner.
     * @param v Last clock
     */
    @Override
    public synchronized void batchReadyForTransfer(int[] v) throws RemoteException{
        flagBringProductsFromFactory += 1;
        sharedInfo.setTranfsProductsToShop(true);
        for(int i = 0; i < num_v; i++){
            if(v[i] > v_craftman[i]){
                v_craftman[i] = v[i];
            }
        }
        notifyAll();
    }

    /**
     * The Owner address a Customer on the queue. The owner wakes up the first Customer in the
     * queue.
     * @param v Last clock
     * @return id of the Customer that the Owner is attending
     */
    @Override
    public synchronized int addressACustomer(int[] v){
        attendingCustomerId = (int) this.queueCustomer.peek(); // retorna id do cliente
        this.v_owner = v;
        notifyAll();
        return attendingCustomerId;
    }

    /**
     * The Owner services a customer. The Owner waits until the Customer updates the number of
     * products he wants to buy.
     *
     * @return number of goods that the customer is buying
     */
    @Override
    public synchronized int serviceCustomer(){
        while (nGoodsCustomerHave < 0){
            try{
                wait();
            } catch (Exception e){
            }
        }
        return nGoodsCustomerHave;
    }

    /**
     * The Owner says goodbye to the Customer he is attending. He updates the flag that indicates
     * that the purchase was made and wakes up the Customer. Then removes the Customer from the
     * queue.
     * @param v Last clock
     */
    @Override
    public synchronized void sayGoodByeToCustomer(int[] v){
        flagPurchaseMade = true;
        this.v_owner = v;
        notifyAll();
        removeSitCustomer(attendingCustomerId);
        attendingCustomerId = -1;
    }

    // Funcions of customer
    /**
     * The Customer sees if the door is open
     *
     * @return True if the shop is OPEN
     */
    @Override
    public synchronized boolean isDoorOpen(){
        return shopState == OPEN;
    }

    /**
     * The customer enters the shop.
     */
    @Override
    public synchronized void enterShop() throws RemoteException{
        this.customerInsideShop++;
        sharedInfo.setnCustomersInsideShop(customerInsideShop);
    }

    /**
     * The Customer is perusing around. A random number is generated, then if it is less than 0.5
     * and exists goods to buy, that number*100 is divided by the number of goods for sell and
     * returns his remainder.
     *
     * @return number of goods to buy
     */
    @Override
    public synchronized int perusingAround() throws RemoteException{
        // choose what to buy
        double r = Math.random();
        if ((r < 0.5) && (nGoodsInDisplay > 0)){ // 50% probability to buy
            r = r * 10;
            int goods = (int) (r + 1) % nGoodsInDisplay;
            if (nGoodsInDisplay == 1) // When there is only one product to sell
            {
                goods = 1;
            }
            //System.out.printf("\t\tR : %f - Goods : %d - Produtos Disponiveis: %d\n",r,goods,nGoodsInDisplay);
            nGoodsInDisplay -= goods;
            sharedInfo.setnGoodsInDisplay(nGoodsInDisplay);
            return goods;
        }
        return 0;
    }

    /**
     * The Customer goes to the queue, and waits till the owner call him. When the owner calls a
     * Customer and he is in the front of the queue, he makes the purchase.
     * @param v Last clock
     * @param customerId Id of the customer that wants to buy something
     * @param nGoods Number of goods that the Customer wants to buy
     */
    @Override
    public synchronized void iWantThis(int customerId, int nGoods, int[] v){

        this.queueCustomer.write(customerId); // Goes to the queue
        this.v_customer = v;
        notifyAll(); // wakes up the Owner
        while (attendingCustomerId != customerId){
            try{
                wait(); // Waits until the Owner calls the next Customer
                Thread.sleep(1000);
            } catch (InterruptedException ex){
            }
        }

        flagPurchaseMade = false;
        nGoodsCustomerHave = nGoods;
        this.v_customer_queue[customerId] = v;
        notifyAll();
        while (!flagPurchaseMade){
            try{
                wait();
                Thread.sleep(1000);
            } catch (Exception e){
            }
        }
        nGoodsCustomerHave = -1;
    }

    /**
     * Remove Customer from queue.
     *
     * @param customerId id
     */
    @Override
    public synchronized void removeSitCustomer(int customerId){
        if ((int) queueCustomer.peek() == customerId) // Removes the correct Customer Id
        {
            queueCustomer.read();
        }
    }

    /**
     * The Customer leaves the Shop. If he his the last one leaving the Shop, wakes up the Owner
     * (important to Owner finish working, if he his waiting for all the Customers to buy products,
     * but the last one don't buy anything).
     * @param v Last clock
     */
    @Override
    public synchronized void exitShop(int[] v) throws RemoteException{
        customerInsideShop--;
        sharedInfo.setnCustomersInsideShop(customerInsideShop);
        if (!customersInTheShop()){
            this.v_customer = v;
            notifyAll();
        }
    }

    /**
     * Set present Shop state.
     *
     * @param state State of the Shop
     */
    @Override
    public synchronized void setShopState(int state) throws RemoteException{
        shopState = state;
        sharedInfo.setShopState(state);
    }

    /**
     * Check if the factory needs prime materials.
     *
     * @return true if the Factory needs prime materials
     */
    @Override
    public boolean isSupplyMaterialsToFactory(){
        return flagPrimeMaterialsNeeded;
    }

    /**
     * Check if the owner can collect products.
     *
     * @return true if he needs to go to the factory
     */
    @Override
    public boolean isTranfsProductsToShop(){
        return flagBringProductsFromFactory > 0;
    }

    /**
     * See if the owner and the customer can stop. Checks if all products have been transferred to
     * Shop, and all the products are sold.
     *
     * @return true if they can stop working
     */
    @Override
    public boolean endOper(){
        return nProductsDelivered == totalProducts && nGoodsInDisplay == 0;
    }
    
    /**
     * Get he last clock stored in the shop of the Craftman.
     * @return last clock stored in the shop
     */
    @Override
    public int[] getClockCraftman() throws RemoteException{
        return v_craftman;
    }
    
    /**
     * Get he last clock stored in the shop of the Owner.
     * @return last clock stored in the shop
     */
    @Override
    public int[] getClockOwner() throws RemoteException{
        return v_owner;
    }
    
    /**
     * Get he last clock stored in the shop of the Customer.
     * @return last clock stored in the shop
     */
    @Override
    public int[] getClockCustomer() throws RemoteException{
        return v_customer;
    }
    
    /**
     * Get he last clock stored in the shop of the Customer the Owner is attending.
     * @param customerId Id of the customer
     * @return last clock stored in the shop
     */
    @Override
    public int[] getClockCustomer(int customerId) throws RemoteException{
        return v_customer_queue[customerId];
    }
}
