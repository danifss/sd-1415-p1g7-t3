package Registry;

import Interfaces.ConfigurationsInterface;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 3.0
 */

public class Configurations implements ConfigurationsInterface {

    /**
     * Logging file name
     * @serial fName
     */
	private String fName = "log.txt";
    
    /**
     * Number of Craftmans.
     * @serial nCraftmans
     */
	private int nCraftmans = 3;
    
    /**
     * Number of Customers
     * @serial nCustomers
     */
    private int nCustomers = 3;
    
    /**
     * Initial number of prime materials in the Factory
     * @serial nPrimeMaterialsInFactory
     */
    private int nPrimeMaterialsInFactory = 10;
    
    /**
     * Initial number of products in the Shop
     * @serial nInitialProductsInShop
     */
    private int nInitialProductsInShop = 10;
    
    /**
     * Initial number of prime materials in the Storage
     * @serial nInitialPrimeMaterialsInStorage
     */
    private int nInitialPrimeMaterialsInStorage = 20;
    
    /**
     * Prime materials needed per product
     * @serial nPrimeMaterialsByProduct
     */
    private int nPrimeMaterialsByProduct = 2;
    
    /**
     * Minimum number of prime materials for restock
     * @serial nMinPrimeMaterialsForRestock
     */
    private int nMinPrimeMaterialsForRestock = 10;
    
    /**
     * Maximum number of products that the owner can carry
     * @serial nMaxProductsCollect
     */
    private int nMaxProductsCollect = 5;
    
    /**
     * Number of total products
     * @serial totalProducts
     */
    private int totalProducts = 0;
    
    
    // PORTS
    /**
     * Register service host name
     * @serial RMIREGHOSTNAME
     */
    public String RMIREGHOSTNAME = "localhost";
    
    /**
     * Register service port number
     * @serial RMIREGPORTNUMB
     */
    public int RMIREGPORTNUMB = 22170;
    
    /**
     * Listening port of RMI Registry
     * @serial REGISTERPORT
     */
    private final int REGISTERPORT = 22171;
    
    /**
     * Listening port of RMI Reposiroty
     * @serial REPOSITORYPORT
     */
    private final int REPOSITORYPORT = 22172;
    
    /**
     * Listening port of RMI Shop
     * @serial SHOPPORT
     */
    private final int SHOPPORT = 22173;
    
    /**
     * Listening port of RMI Factory
     * @serial FACTORYPORT
     */
    private final int FACTORYPORT = 22174;
    
    /**
     * Listening port of RMI Storage
     * @serial STORAGEPORT
     */
    private final int STORAGEPORT = 22175;


//    /**
//     * Configurations Server Constructor with parameters
//     * 
//     * @param rmiRegHostName
//     * @param rmiRegPortNumb
//     * @param fName
//     * @param nCraftmans
//     * @param nCustomers
//     * @param nPrimeMaterialsInFactory
//     * @param nInitialProductsInShop
//     * @param nInitialPrimeMaterialsInStorage
//     * @param nPrimeMaterialsByProduct
//     * @param nMinPrimeMaterialsForRestock
//     * @param nMaxProductsCollect
//     */
//	public Configurations(String rmiRegHostName, int rmiRegPortNumb, String fName, int nCraftmans, int nCustomers, int nPrimeMaterialsInFactory, int nInitialProductsInShop, int nInitialPrimeMaterialsInStorage, int nPrimeMaterialsByProduct, int nMinPrimeMaterialsForRestock, int nMaxProductsCollect){
//        this.RMIREGHOSTNAME = rmiRegHostName;
//        this.RMIREGPORTNUMB = rmiRegPortNumb;
//        this.fName = fName;
//        this.nCraftmans = nCraftmans;
//        this.nCustomers = nCustomers;
//        this.nPrimeMaterialsInFactory = nPrimeMaterialsInFactory;
//        this.nInitialProductsInShop = nInitialProductsInShop;
//        this.nInitialPrimeMaterialsInStorage = nInitialPrimeMaterialsInStorage;
//        this.nPrimeMaterialsByProduct = nPrimeMaterialsByProduct;
//        this.nMinPrimeMaterialsForRestock = nMinPrimeMaterialsForRestock;
//        this.nMaxProductsCollect = nMaxProductsCollect;
//        this.totalProducts = ((nPrimeMaterialsInFactory + nInitialPrimeMaterialsInStorage / nPrimeMaterialsByProduct) + nInitialProductsInShop);
//    }


    /**
     * Logging file name.
     * @param fName file name
     */
    @Override
    public void setfName(String fName){
        this.fName = fName;
    }


    /**
     * Number of Craftmans.
     * @param nCraftmans number of craftmans
     */
    @Override
    public void setnCraftmans(int nCraftmans){
        this.nCraftmans = nCraftmans;
    }


    /**
     * Number of Customers.
     * @param nCustomers number of customers
     */
    @Override
    public void setnCustomers(int nCustomers){
        this.nCustomers = nCustomers;
    }

    /**
     * Set num Prime Materials In Factory.
     * @param nPrimeMaterialsInFactory prime materials
     */
    @Override
    public void setnPrimeMaterialsInFactory(int nPrimeMaterialsInFactory){
        this.nPrimeMaterialsInFactory = nPrimeMaterialsInFactory;
    }


    /**
     * Set num Initial Products In Shop.
     * @param nInitialProductsInShop products in Shop
     */
    @Override
    public void setnInitialProductsInShop(int nInitialProductsInShop){
        this.nInitialProductsInShop = nInitialProductsInShop;
    }


    /**
     * Set num Initial Prime Materials In Storage.
     * @param nInitialPrimeMaterialsInStorage prime materials in Storage
     */
    @Override
    public void setnInitialPrimeMaterialsInStorage(int nInitialPrimeMaterialsInStorage){
        this.nInitialPrimeMaterialsInStorage = nInitialPrimeMaterialsInStorage;
    }

    /**
     * Set num Prime Materials By Product.
     * @param nPrimeMaterialsByProduct prime materials by product
     */
    @Override
    public void setnPrimeMaterialsByProduct(int nPrimeMaterialsByProduct){
        this.nPrimeMaterialsByProduct = nPrimeMaterialsByProduct;
    }

    /**
     * Set num Max Products Collect.
     * @param nMaxProductsCollect Max Products Collect
     */
    @Override
    public void setnMaxProductsCollect(int nMaxProductsCollect){
        this.nMaxProductsCollect = nMaxProductsCollect;
    }

    /**
     * Set num Min Prime Materials For Restock.
     * @param nMinPrimeMaterialsForRestock Min Prime Materials For Restock
     */
    @Override
    public void setnMinPrimeMaterialsForRestock(int nMinPrimeMaterialsForRestock){
        this.nMinPrimeMaterialsForRestock = nMinPrimeMaterialsForRestock;
    }

    /**
     * Set total Products.
     * @param totalProducts total Products
     */
    @Override
    public void setTotalProducts(int totalProducts){
        this.totalProducts = totalProducts;
    }
    
    
    
    
    /**
     * Get logging file name.
     * @return log name
     */
    @Override
    public String getfName(){
        return fName;
    }
    
    /**
     * Number of Craftmans.
     * @return number of craftmans
     */
    @Override
    public int getnCraftmans(){
        return nCraftmans;
    }
    
    /**
     * Number of Customers.
     * @return number of customers
     */
    @Override
    public int getnCustomers(){
        return nCustomers;
    }
    
    /**
     * Num Prime Materials In Factory.
     * @return nPrimeMaterialsInFactory
     */
    @Override
    public int getnPrimeMaterialsInFactory(){
        return nPrimeMaterialsInFactory;
    }
    
    /**
     * Get num Initial Products In Shop.
     * @return nInitialProductsInShop products in Shop
     */
    @Override
    public int getnInitialProductsInShop(){
        return nInitialProductsInShop;
    }
    
    /**
     * Get num Initial Prime Materials In Storage.
     * @return nInitialPrimeMaterialsInStorage prime materials in Storage
     */
    @Override
    public int getnInitialPrimeMaterialsInStorage(){
        return nInitialPrimeMaterialsInStorage;
    }
    
    /**
     * Get num Prime Materials By Product.
     * @return nPrimeMaterialsByProduct prime materials by product
     */
    @Override
    public int getnPrimeMaterialsByProduct(){
        return nPrimeMaterialsByProduct;
    }
    
    /**
     * Get num Max Products Collect.
     * @return nMaxProductsCollect Max Products Collect
     */
    @Override
    public int getnMaxProductsCollect(){
        return nMaxProductsCollect;
    }
    
    /**
     * Get num Min Prime Materials For Restock.
     * @return nMinPrimeMaterialsForRestock Min Prime Materials For Restock
     */
    @Override
    public int getnMinPrimeMaterialsForRestock(){
        return nMinPrimeMaterialsForRestock;
    }

    /**
     * Get total Products.
     * @return totalProducts total Products
     */
    @Override
    public int getTotalProducts(){
        return totalProducts;
    }

    
    
    @Override
    public int getREGISTERPORT(){
        return REGISTERPORT;
    }

    @Override
    public int getREPOSITORYPORT(){
        return REPOSITORYPORT;
    }

    @Override
    public int getSHOPPORT(){
        return SHOPPORT;
    }

    @Override
    public int getFACTORYPORT(){
        return FACTORYPORT;
    }

    @Override
    public int getSTORAGEPORT(){
        return STORAGEPORT;
    }
}
