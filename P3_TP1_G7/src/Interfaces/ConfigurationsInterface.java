/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author silva
 */
public interface ConfigurationsInterface extends Remote {

    /**
     * Get logging file name.
     * @return log name
     */
    String getfName() throws RemoteException;

    /**
     * Number of Craftmans.
     * @return number of craftmans
     */
    int getnCraftmans() throws RemoteException;

    /**
     * Number of Customers.
     * @return number of customers
     */
    int getnCustomers() throws RemoteException;

    /**
     * Get num Initial Prime Materials In Storage.
     * @return nInitialPrimeMaterialsInStorage prime materials in Storage
     */
    int getnInitialPrimeMaterialsInStorage() throws RemoteException;

    /**
     * Get num Initial Products In Shop.
     * @return nInitialProductsInShop products in Shop
     */
    int getnInitialProductsInShop() throws RemoteException;

    /**
     * Get num Max Products Collect.
     * @return nMaxProductsCollect Max Products Collect
     */
    int getnMaxProductsCollect() throws RemoteException;

    /**
     * Get num Min Prime Materials For Restock.
     * @return nMinPrimeMaterialsForRestock Min Prime Materials For Restock
     */
    int getnMinPrimeMaterialsForRestock() throws RemoteException;

    /**
     * Get num Prime Materials By Product.
     * @return nPrimeMaterialsByProduct prime materials by product
     */
    int getnPrimeMaterialsByProduct() throws RemoteException;

    /**
     * Num Prime Materials In Factory.
     * @return nPrimeMaterialsInFactory
     */
    int getnPrimeMaterialsInFactory() throws RemoteException;

    /**
     * Get total Products.
     * @return totalProducts total Products
     */
    int getTotalProducts() throws RemoteException;

    /**
     * Logging file name.
     * @param fName file name
     */
    void setfName(String fName) throws RemoteException;

    /**
     * Number of Craftmans.
     * @param nCraftmans number of craftmans
     */
    void setnCraftmans(int nCraftmans) throws RemoteException;

    /**
     * Number of Customers.
     * @param nCustomers number of customers
     */
    void setnCustomers(int nCustomers) throws RemoteException;

    /**
     * Set num Initial Prime Materials In Storage.
     * @param nInitialPrimeMaterialsInStorage prime materials in Storage
     */
    void setnInitialPrimeMaterialsInStorage(int nInitialPrimeMaterialsInStorage) throws RemoteException;

    /**
     * Set num Initial Products In Shop.
     * @param nInitialProductsInShop products in Shop
     */
    void setnInitialProductsInShop(int nInitialProductsInShop) throws RemoteException;

    /**
     * Set num Max Products Collect.
     * @param nMaxProductsCollect Max Products Collect
     */
    void setnMaxProductsCollect(int nMaxProductsCollect) throws RemoteException;

    /**
     * Set num Min Prime Materials For Restock.
     * @param nMinPrimeMaterialsForRestock Min Prime Materials For Restock
     */
    void setnMinPrimeMaterialsForRestock(int nMinPrimeMaterialsForRestock) throws RemoteException;

    /**
     * Set num Prime Materials By Product.
     * @param nPrimeMaterialsByProduct prime materials by product
     */
    void setnPrimeMaterialsByProduct(int nPrimeMaterialsByProduct) throws RemoteException;

    /**
     * Set num Prime Materials In Factory.
     * @param nPrimeMaterialsInFactory prime materials
     */
    void setnPrimeMaterialsInFactory(int nPrimeMaterialsInFactory) throws RemoteException;

    /**
     * Set total Products.
     * @param totalProducts total Products
     */
    void setTotalProducts(int totalProducts) throws RemoteException;
    
    
    
    
    int getREGISTERPORT() throws RemoteException;

    int getREPOSITORYPORT() throws RemoteException;

    int getSHOPPORT() throws RemoteException;

    int getFACTORYPORT() throws RemoteException;

    int getSTORAGEPORT() throws RemoteException;
}
