package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 3.0
 */
public interface FactoryInterface extends Remote {

    // CRAFTMAN
    /**
     * The Craftman indicates that the owner has products to collect. He increments the number of
     * flagNProductsCall to tell that the Owner needs to come to the Factory flagNProductsCall times
     * to collect products.
     * @return true if he really needs to contact the Owner
     * @throws java.rmi.RemoteException Remote Exception
     */
    boolean batchReadyForTransfer() throws RemoteException;

    /**
     * The Craftman verifies if he needs to contact the owner to collect products. Checks if the
     * number of finished products in factory divided by the number of products that the owner can
     * collect is different of flagNProductsCall (integer division). He also checks if he made the
     * last product. If one of the conditions are true, he needs to contact the Owner.
     *
     * @return true if he needs to contact
     * @throws java.rmi.RemoteException Remote Exception
     */
    boolean checkContactProduct() throws RemoteException;

    /**
     * The Craftman checks if the Factory has prime materials to collect. If the number of prime
     * materials in the Factory is less than the number of prime materials needed per product, the
     * Craftman will wait until the Owner brings new prime materials and wakes up the Craftman. If
     * all prime materials were already supplied, the Craftman will not wait and he will stop
     * working
     *
     * @return true if has materials
     * @throws java.rmi.RemoteException Remote Exception
     */
    boolean checkForMaterials() throws RemoteException;

    /**
     * Check if the Craftman needs to contact owner to bring prime materials. The Craftman needs to
     * contact the Owner if the number of prime materials available in the Factory is less than the
     * minimum number of prime materials in stock to call owner to restock, and the number of prime
     * materials supplied is less than the total number of prime materials available in the storage
     * at the beginning.
     *
     * @return true if needs to restock
     * @throws java.rmi.RemoteException Remote Exception
     */
    boolean checkForRestock() throws RemoteException;

    /**
     * The Craftman collects prime materials. He checks again if there is prime materials (to avoid
     * that someone before him already took his prime materials), and then he collects, decreasing
     * the number of pieces collected in the number of prime materials available in the Factory.
     *
     * @return number of collected prime materials
     * @throws java.rmi.RemoteException Remote Exception
     */
    int collectMaterials() throws RemoteException;

    /**
     * Checks if the all the prime materials from the storage were supplied. This function helps the
     * Craftman to know if he can stop working.
     *
     * @return true if there is no more prime materials in the storage
     * @throws java.rmi.RemoteException Remote Exception
     */
    boolean endOfPrimeMaterials() throws RemoteException;

    /**
     * The Craftman sees if someone already contacted the owner to restock prime materials.
     *
     * @return true if someone already contacted the owner
     * @throws java.rmi.RemoteException Remote Exception
     */
    boolean flagPrimeActivated() throws RemoteException;

    /**
     * Craftman sees how many prime materials needs to produce a new product
     *
     * @return number of prime materials needed per products
     * @throws java.rmi.RemoteException Remote Exception
     */
    int getnPrimePerProduct() throws RemoteException;

    /**
     * The Craftman stores the products produced. The Craftman adds the number of products produced
     * that he has at the moment to the number of finished products in the Factory and to the number
     * of the total products produced.
     *
     * @param nProd Number of products that the Craftman has
     * @return number of products the Craftman stored
     * @throws java.rmi.RemoteException Remote Exception
     */
    int goToStore(int nProd) throws RemoteException;

    /**
     * The Craftman turns true the flag that indicates that prime materials are needed. He also
     * ensure that none of the Craftmans already contacted the Owner.
     *
     * @return true if he contacted the owner
     * @throws java.rmi.RemoteException Remote Exception
     */
    boolean primeMaterialsNeeded() throws RemoteException;

    // OWNER
    /**
     * Owner brings prime materials. He increases the number of prime materials in Factory, and also
     * the total number of prime materials supplied and the number of times he came to the Factory.
     *
     * @param nPrimeMaterials Amount of prime materials to restock
     * @param v Last clock
     * @throws java.rmi.RemoteException Remote Exception
     */
    void replenishStock(int nPrimeMaterials, int[] v) throws RemoteException;

    /**
     * Owner goes to factory to collect finished products. If he can collect all the products
     * (depends of the number of products that the owner can carry), he collect all products setting
     * the number of finished products in Factory to zero. If he can't collect all the products, he
     * collect the number of products he can, decreasing from the number of finished products the
     * number of products collected. He also decreases the flagNProductsCall.
     *
     * @return number of products collected
     * @throws java.rmi.RemoteException Remote Exception
     */
    int goToWorkshop() throws RemoteException;
    
    /**
     * Get he last clock stored in the factory.
     * @return last clock stored in the factory
     * @throws java.rmi.RemoteException Remote Exception
     */
    int[] getClock() throws RemoteException;
}
