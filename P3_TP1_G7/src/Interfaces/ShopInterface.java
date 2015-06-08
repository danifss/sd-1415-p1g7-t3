package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 3.0
 */
public interface ShopInterface extends Remote {

    // CRAFTMAN

    /**
     * The Craftman indicates that the Owner can go to factory to collect products. He changes the
     * flag and wakes up the Owner.
     * @param v Last clock
     * @throws java.rmi.RemoteException Remote Exception
     */
    void batchReadyForTransfer(int[] v) throws RemoteException;

    /**
     * The Craftman indicates that prime materials is needed in the Factory. He changes the flag and
     * wakes up the Owner.
     * @param v Last clock
     * @throws java.rmi.RemoteException Remote Exception
     */
    void primeMaterialsNeeded(int[] v) throws RemoteException;

    // CUSTOMER
    /**
     * The customer enters the shop.
     *
     * @throws java.rmi.RemoteException Remote Exception
     */
    void enterShop() throws RemoteException;

    /**
     * The Customer leaves the Shop. If he his the last one leaving the Shop, wakes up the Owner
     * (important to Owner finish working, if he his waiting for all the Customers to buy products,
     * but the last one don't buy anything).
     * @param v Last clock
     * @throws java.rmi.RemoteException Remote Exception
     */
    void exitShop(int[] v) throws RemoteException;

    /**
     * The Customer goes to the queue, and waits till the owner call him. When the owner calls a
     * Customer and he is in the front of the queue, he makes the purchase.
     *
     * @param customerId Id of the customer that wants to buy something
     * @param nGoods Number of goods that the Customer wants to buy
     * @param v Last clock
     * @throws java.rmi.RemoteException Remote Exception
     */
    void iWantThis(int customerId, int nGoods, int[] v) throws RemoteException;

    /**
     * The Customer sees if the door is open
     *
     * @return True if the shop is OPEN
     * @throws java.rmi.RemoteException Remote Exception
     */
    boolean isDoorOpen() throws RemoteException;

    /**
     * The Customer is perusing around. A random number is generated, then if it is less than 0.5
     * and exists goods to buy, that number*100 is divided by the number of goods for sell and
     * returns his remainder.
     *
     * @return number of goods to buy
     * @throws java.rmi.RemoteException Remote Exception
     */
    int perusingAround() throws RemoteException;
    

    // OWNER
    /**
     * Set present Shop state.
     *
     * @param state State of the Shop
     * @throws java.rmi.RemoteException Remote Exception
     */
    void setShopState(int state) throws RemoteException;
    
    /**
     * Owner opens the door. The state of the Shop is changed to OPEN.
     * @throws java.rmi.RemoteException Remote Exception
     */
    void openTheDoor() throws RemoteException;

    /**
     * Check if the factory needs prime materials.
     *
     * @return true if the Factory needs prime materials
     * @throws java.rmi.RemoteException Remote Exception
     */
    boolean isSupplyMaterialsToFactory() throws RemoteException;

    /**
     * Check if the owner can collect products.
     *
     * @return true if he needs to go to the factory
     * @throws java.rmi.RemoteException Remote Exception
     */
    boolean isTranfsProductsToShop() throws RemoteException;

    /**
     * Owner sees the situation of the shop and decide what to do. If the shop is open, he waits
     * until a Customer or a Craftman calls him. If the shop is still open, he wait until all
     * Customers leave the shop, and then proceeds to the request from the Factory.
     * FACTORY_NEEDS_SOMETHING can also indicates that the Owner can stop, because this action makes
     * the Owner close the Shop.
     *
     * @return action to do
     * @throws java.rmi.RemoteException Remote Exception
     */
    int appraiseSit() throws RemoteException;

    /**
     * Owner closes the door. If the shop has Customers inside, the shop change his state to
     * STILL_OPEN, if the shop doesn't have Customers, the shop change his state to CLOSED.
     * @throws java.rmi.RemoteException Remote Exception
     */
    void closeTheDoor() throws RemoteException;

    /**
     * See if there is customers inside the shop.
     *
     * @return true if the number of Customers inside the shop is greater than 0
     * @throws java.rmi.RemoteException Remote Exception
     */
    boolean customersInTheShop() throws RemoteException;
    
    /**
     * See if the Queue has customers.
     *
     * @return true if the queue has customers
     * @throws java.rmi.RemoteException Remote Exception
     */
    boolean customerInTheQueue() throws RemoteException;

    /**
     * The Owner address a Customer on the queue. The owner wakes up the first Customer in the
     * queue.
     * @param v Last clock
     * @return id of the Customer that the Owner is attending
     * @throws java.rmi.RemoteException Remote Exception
     */
    int addressACustomer(int[] v) throws RemoteException;

    /**
     * The Owner services a customer. The Owner waits until the Customer updates the number of
     * products he wants to buy.
     *
     * @return number of goods that the customer is buying
     * @throws java.rmi.RemoteException Remote Exception
     */
    int serviceCustomer() throws RemoteException;

    /**
     * The Owner says goodbye to the Customer he is attending. He updates the flag that indicates
     * that the purchase was made and wakes up the Customer. Then removes the Customer from the
     * queue.
     * @param v Last clock
     * @throws java.rmi.RemoteException Remote Exception
     */
    void sayGoodByeToCustomer(int[] v) throws RemoteException;
    
    /**
     * Remove Customer from queue.
     *
     * @param customerId id
     * @throws java.rmi.RemoteException Remote Exception
     */
    void removeSitCustomer(int customerId) throws RemoteException;

    /**
     * See if the shop is on state STILL_OPEN.
     *
     * @return true if the shop is STILL_OPEN
     * @throws java.rmi.RemoteException Remote Exception
     */
    boolean isShopStillOpen() throws RemoteException;

    /**
     * The owner goes to the Factory to collect products.
     * @throws java.rmi.RemoteException Remote Exception
     */
    void goToWorkshop() throws RemoteException;

    /**
     * Update the number of products that the shop is selling.
     *
     * @param goods Number of products to add to the number of products in display
     * @throws java.rmi.RemoteException Remote Exception
     */
    void addnGoodsInDisplay(int goods) throws RemoteException;

    /**
     * Owner goes to Factory to restock prime materials.
     * @throws java.rmi.RemoteException Remote Exception
     */
    void replenishStock() throws RemoteException;

    /**
     * See if the owner and the customer can stop. Checks if all products have been transferred to
     * Shop, and all the products are sold.
     *
     * @return true if they can stop working
     * @throws java.rmi.RemoteException Remote Exception
     */
    boolean endOper() throws RemoteException;
    
    /**
     * Get he last clock stored in the Shop.
     * @return last clock stored in the Shop
     * @throws java.rmi.RemoteException Remote Exception
     */
    int[] getClockCraftman() throws RemoteException;

    /**
     * Get he last clock stored in the shop of the Owner.
     * @return last clock stored in the Shop
     * @throws java.rmi.RemoteException Remote Exception
     */
    int[] getClockOwner() throws RemoteException;
    
    /**
     * Get he last clock stored in the shop of the Customer.
     * @return last clock stored in the Shop
     * @throws java.rmi.RemoteException Remote Exception
     */
    int[] getClockCustomer() throws RemoteException;
    
    /**
     * Get he last clock stored in the shop of the Customer the Owner is attending.
     * @param customerId Id of the customer
     * @return last clock stored in the Shop
     * @throws java.rmi.RemoteException Remote Exception
     */
    int[] getClockCustomer(int customerId) throws RemoteException;
}
