package Interfaces;

import java.rmi.Remote;

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
     */
    void setCraftmanState(int craftmanId, int state);

    /**
     * Change the number of products (accumulation) manufactured by the craftsman[i].
     *
     * @param craftmanId Craftman id
     * @param nGoodsCraftedByCraftman total number of the products crafted by the Craftman
     */
    void setnGoodsCraftedByCraftman(int craftmanId, int nGoodsCraftedByCraftman);

    // CUSTOMER
    /**
     * Set Customer[i] State.
     *
     * @param customerId Customer id
     * @param state State of the Customer
     */
    void setCustomerState(int customerId, int state);

    /**
     * Set number of customers inside.
     *
     * @param customerId id
     * @param nCustomersInsideShop Number of customers inside
     */
    void setnCustomersInsideShop(int customerId, int nCustomersInsideShop);

    /**
     * Set number of goods (accumulation) bought by the customer.
     *
     * @param customerId Customer id
     * @param nGoods Number of goods bought by the customer
     */
    void setnGoodsByCustomer(int customerId, int nGoods);

    // OWNER
    /**
     * Set Owner State.
     *
     * @param state State of the Owner
     */
    void setOwnerState(int state);

    // SHOP
    /**
     * Set if the craftsman requested the transfer of finished products to the Shop.
     *
     * @param tranfsProductsToShop Boolean indicating if the Craftman requested
     */
    void setTranfsProductsToShop(boolean tranfsProductsToShop);

    /**
     * Set number of goods in display.
     *
     * @param nGoodsInDisplay Number of goods in display
     */
    void setnGoodsInDisplay(int nGoodsInDisplay);

    /**
     * Set if the craftsman requested the supply of prime materials to the Factory.
     *
     * @param supplyMaterialsToFactory Boolean indicating if the Craftman requested
     */
    void setSupplyMaterialsToFactory(boolean supplyMaterialsToFactory);

    /**
     * Set number of customers inside.
     *
     * @param nCustomersInsideShop Number of customers inside
     */
    void setnCustomersInsideShop(int nCustomersInsideShop);

    /**
     * Set Shop State.
     *
     * @param state State of the shop
     */
    void setShopState(int state);

    // FACTORY
    /**
     * Change the amount of prime materials presently in the Factory.
     *
     * @param nPrimeMaterialsInFactory Amount of prime materials available in the Factory
     */
    void setnPrimeMaterialsInFactory(int nPrimeMaterialsInFactory);

    /**
     * Change the number of finished products presently in the Factory.
     *
     * @param nFinishedProductsInFactory Number of finished products in the Factory
     */
    void setnFinishedProductsInFactory(int nFinishedProductsInFactory);

    /**
     * Change the total number of products that have already been manufactured (accumulation).
     *
     * @param nProductsManufactured Total number of products produced
     */
    void setnProductsManufactured(int nProductsManufactured);

    /**
     * Change the number of times that a supply of prime materials was delivered to the Factory.
     *
     * @param nSuppliedTimes Number of times that the owner delivered prime materials
     */
    void setnSuppliedTimes(int nSuppliedTimes);

    /**
     * Change the total amount of prime materials that have already been supplied (accumulation).
     *
     * @param nPrimeMaterialsSupplied Number of prime materials supplied
     */
    void setnPrimeMaterialsSupplied(int nPrimeMaterialsSupplied);

    // STORAGE
    /**
     * Get the amount of prime materials that the owner should collect during the experience.
     *
     * @return number of prime materials to deliver
     */
    int getnMaxPrimeMaterialsToDeliver();

    /**
     * Get the number of prime materials already delivered.
     *
     * @return number of prime materials delivered
     */
    int getnPrimeMaterialsDelivered();

    /**
     * See if the storage has prime materials.
     *
     * @return true if the storage has prime materials
     */
    boolean isPrimeMaterialsAvailabe();

    /**
     * Owner visit suppliers and get some prime materials to be delivered in the factory. If the
     * storage has more or the same prime materials than the number of prime materials that the
     * Owner can carry, the Owner collects all prime materials he can. If the storage has less than
     * the number of prime materials that the Owner can carry, he collects the prime materials
     * available.
     *
     * @return Number of prime materials collected
     */
    int visitSuppliers();

}
