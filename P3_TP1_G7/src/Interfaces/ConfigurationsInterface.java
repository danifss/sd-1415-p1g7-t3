/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 3.0
 */
public interface ConfigurationsInterface extends Remote {

    /**
     * Get logging file name.
     * @return log name
     * @throws RemoteException Remote Exception
     */
    String getfName() throws RemoteException;

    /**
     * Number of Craftmans.
     * @return number of craftmans
     * @throws RemoteException Remote Exception
     */
    int getnCraftmans() throws RemoteException;

    /**
     * Number of Customers.
     * @return number of customers
     * @throws RemoteException Remote Exception
     */
    int getnCustomers() throws RemoteException;

    /**
     * Get num Initial Prime Materials In Storage.
     * @return nInitialPrimeMaterialsInStorage prime materials in Storage
     * @throws RemoteException Remote Exception
     */
    int getnInitialPrimeMaterialsInStorage() throws RemoteException;

    /**
     * Get num Initial Products In Shop.
     * @return nInitialProductsInShop products in Shop
     * @throws RemoteException Remote Exception
     */
    int getnInitialProductsInShop() throws RemoteException;

    /**
     * Get num Max Products Collect.
     * @return nMaxProductsCollect Max Products Collect
     * @throws RemoteException Remote Exception
     */
    int getnMaxProductsCollect() throws RemoteException;

    /**
     * Get num Min Prime Materials For Restock.
     * @return nMinPrimeMaterialsForRestock Min Prime Materials For Restock
     * @throws RemoteException Remote Exception
     */
    int getnMinPrimeMaterialsForRestock() throws RemoteException;

    /**
     * Get num Prime Materials By Product.
     * @return nPrimeMaterialsByProduct prime materials by product
     * @throws RemoteException Remote Exception
     */
    int getnPrimeMaterialsByProduct() throws RemoteException;

    /**
     * Num Prime Materials In Factory.
     * @return nPrimeMaterialsInFactory
     * @throws RemoteException Remote Exception
     */
    int getnPrimeMaterialsInFactory() throws RemoteException;

    /**
     * Get total Products.
     * @return totalProducts total Products
     * @throws RemoteException Remote Exception
     */
    int getTotalProducts() throws RemoteException;

    /**
     * Logging file name.
     * @param fName file name
     * @throws RemoteException Remote Exception
     */
    void setfName(String fName) throws RemoteException;

    /**
     * Number of Craftmans.
     * @param nCraftmans number of craftmans
     * @throws RemoteException Remote Exception
     */
    void setnCraftmans(int nCraftmans) throws RemoteException;

    /**
     * Number of Customers.
     * @param nCustomers number of customers
     * @throws RemoteException Remote Exception
     */
    void setnCustomers(int nCustomers) throws RemoteException;

    /**
     * Set num Initial Prime Materials In Storage.
     * @param nInitialPrimeMaterialsInStorage prime materials in Storage
     * @throws RemoteException Remote Exception
     */
    void setnInitialPrimeMaterialsInStorage(int nInitialPrimeMaterialsInStorage) throws RemoteException;

    /**
     * Set num Initial Products In Shop.
     * @param nInitialProductsInShop products in Shop
     * @throws RemoteException Remote Exception
     */
    void setnInitialProductsInShop(int nInitialProductsInShop) throws RemoteException;

    /**
     * Set num Max Products Collect.
     * @param nMaxProductsCollect Max Products Collect
     * @throws RemoteException Remote Exception
     */
    void setnMaxProductsCollect(int nMaxProductsCollect) throws RemoteException;

    /**
     * Set num Min Prime Materials For Restock.
     * @param nMinPrimeMaterialsForRestock Min Prime Materials For Restock
     * @throws RemoteException Remote Exception
     */
    void setnMinPrimeMaterialsForRestock(int nMinPrimeMaterialsForRestock) throws RemoteException;

    /**
     * Set num Prime Materials By Product.
     * @param nPrimeMaterialsByProduct prime materials by product
     * @throws RemoteException Remote Exception
     */
    void setnPrimeMaterialsByProduct(int nPrimeMaterialsByProduct) throws RemoteException;

    /**
     * Set num Prime Materials In Factory.
     * @param nPrimeMaterialsInFactory prime materials
     * @throws RemoteException Remote Exception
     */
    void setnPrimeMaterialsInFactory(int nPrimeMaterialsInFactory) throws RemoteException;

    /**
     * Set total Products.
     * @param totalProducts total Products
     * @throws RemoteException Remote Exception
     */
    void setTotalProducts(int totalProducts) throws RemoteException;
    
    /**
     * Get register port
     * @return register port
     * @throws RemoteException Remote Exception
     */
    int getREGISTERPORT() throws RemoteException;

    /**
     * Get repository port
     * @return repository port
     * @throws RemoteException Remote Exception
     */
    int getREPOSITORYPORT() throws RemoteException;

    /**
     * Get shop port
     * @return shop port
     * @throws RemoteException Remote Exception
     */
    int getSHOPPORT() throws RemoteException;

    /**
     * Get factory port
     * @return factory port
     * @throws RemoteException Remote Exception
     */
    int getFACTORYPORT() throws RemoteException;

    /**
     * Get storage port
     * @return storage port
     * @throws RemoteException Remote Exception
     */
    int getSTORAGEPORT() throws RemoteException;
}
