package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 3.0
 */
public interface RepositoryInterface extends Remote {

    // CRAFTMAN
    /**
     * Set state of the Craftman[i].
     *
     * @param craftmanId Craftman id
     * @param state State of the Craftman
     * @param v Local clock
     * @throws java.rmi.RemoteException
     */
    void setCraftmanState(int craftmanId, int state, int[] v) throws RemoteException;

    /**
     * Change the number of products (accumulation) manufactured by the craftsman[i].
     *
     * @param craftmanId Craftman id
     * @param nGoodsCraftedByCraftman total number of the products crafted by the Craftman
     * @throws java.rmi.RemoteException
     */
    void setnGoodsCraftedByCraftman(int craftmanId, int nGoodsCraftedByCraftman) throws RemoteException;

    // CUSTOMER
    /**
     * Set Customer[i] State.
     *
     * @param customerId Customer id
     * @param state State of the Customer
     * @param v Local clock
     * @throws java.rmi.RemoteException
     */
    void setCustomerState(int customerId, int state, int[] v)  throws RemoteException;


    /**
     * Set number of goods (accumulation) bought by the customer.
     *
     * @param customerId Customer id
     * @param nGoods Number of goods bought by the customer
     * @throws java.rmi.RemoteException
     */
    void setnGoodsByCustomer(int customerId, int nGoods) throws RemoteException;

    // OWNER
    /**
     * Set Owner State.
     *
     * @param state State of the Owner
     * @param v Local clock
     * @throws java.rmi.RemoteException
     */
    void setOwnerState(int state, int[] v) throws RemoteException;

    // SHOP
    /**
     * Set if the craftsman requested the transfer of finished products to the Shop.
     *
     * @param tranfsProductsToShop Boolean indicating if the Craftman requested
     * @throws java.rmi.RemoteException
     */
    void setTranfsProductsToShop(boolean tranfsProductsToShop) throws RemoteException;

    /**
     * Set number of goods in display.
     *
     * @param nGoodsInDisplay Number of goods in display
     * @throws java.rmi.RemoteException
     */
    void setnGoodsInDisplay(int nGoodsInDisplay) throws RemoteException;

    /**
     * Set if the craftsman requested the supply of prime materials to the Factory.
     *
     * @param supplyMaterialsToFactory Boolean indicating if the Craftman requested
     * @throws java.rmi.RemoteException
     */
    void setSupplyMaterialsToFactory(boolean supplyMaterialsToFactory) throws RemoteException;

    /**
     * Set number of customers inside.
     *
     * @param nCustomersInsideShop Number of customers inside
     * @throws java.rmi.RemoteException
     */
    void setnCustomersInsideShop(int nCustomersInsideShop) throws RemoteException;

    /**
     * Set Shop State.
     *
     * @param state State of the shop
     * @throws java.rmi.RemoteException
     */
    void setShopState(int state) throws RemoteException;

    // FACTORY
    /**
     * Change the amount of prime materials presently in the Factory.
     *
     * @param nPrimeMaterialsInFactory Amount of prime materials available in the Factory
     * @throws java.rmi.RemoteException
     */
    void setnPrimeMaterialsInFactory(int nPrimeMaterialsInFactory) throws RemoteException;

    /**
     * Change the number of finished products presently in the Factory.
     *
     * @param nFinishedProductsInFactory Number of finished products in the Factory
     * @throws java.rmi.RemoteException
     */
    void setnFinishedProductsInFactory(int nFinishedProductsInFactory) throws RemoteException;

    /**
     * Change the total number of products that have already been manufactured (accumulation).
     *
     * @param nProductsManufactured Total number of products produced
     * @throws java.rmi.RemoteException
     */
    void setnProductsManufactured(int nProductsManufactured) throws RemoteException;

    /**
     * Change the number of times that a supply of prime materials was delivered to the Factory.
     *
     * @param nSuppliedTimes Number of times that the owner delivered prime materials
     * @throws java.rmi.RemoteException
     */
    void setnSuppliedTimes(int nSuppliedTimes) throws RemoteException;

    /**
     * Change the total amount of prime materials that have already been supplied (accumulation).
     *
     * @param nPrimeMaterialsSupplied Number of prime materials supplied
     * @throws java.rmi.RemoteException
     */
    void setnPrimeMaterialsSupplied(int nPrimeMaterialsSupplied) throws RemoteException;

    // STORAGE
    /**
     * Get the amount of prime materials that the owner should collect during the experience.
     *
     * @return number of prime materials to deliver
     */
    //int getnMaxPrimeMaterialsToDeliver();

    /**
     * Get the number of prime materials already delivered.
     *
     * @return number of prime materials delivered
     */
    //int getnPrimeMaterialsDelivered();

    /**
     * See if the storage has prime materials.
     *
     * @return true if the storage has prime materials
     */
    //boolean isPrimeMaterialsAvailabe();

    /**
     * Owner visit suppliers and get some prime materials to be delivered in the factory. If the
     * storage has more or the same prime materials than the number of prime materials that the
     * Owner can carry, the Owner collects all prime materials he can. If the storage has less than
     * the number of prime materials that the Owner can carry, he collects the prime materials
     * available.
     *
     * @return Number of prime materials collected
     */
    //int visitSuppliers();

}
