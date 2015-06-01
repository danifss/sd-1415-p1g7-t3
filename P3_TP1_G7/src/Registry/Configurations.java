package Registry;

//import Interfaces.ConfigDataInterface;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 3.0
 */

public final class Configurations { //implements ConfigDataInterface {

    /**
     * Logging file name
     * @serial fName
     */
	private static String fName = "log.txt";
    
    /**
     * Number of Craftmans.
     * @serial nCraftmans
     */
	private static int nCraftmans = 3;
    
    /**
     * Number of Customers
     * @serial nCustomers
     */
    private static int nCustomers = 3;
    
    /**
     * Initial number of prime materials in the Factory
     * @serial nPrimeMaterialsInFactory
     */
    private static int nPrimeMaterialsInFactory = 10;
    
    /**
     * Initial number of products in the Shop
     * @serial nInitialProductsInShop
     */
    private static int nInitialProductsInShop = 10;
    
    /**
     * Initial number of prime materials in the Storage
     * @serial nInitialPrimeMaterialsInStorage
     */
    private static int nInitialPrimeMaterialsInStorage = 20;
    
    /**
     * Prime materials needed per product
     * @serial nPrimeMaterialsByProduct
     */
    private static int nPrimeMaterialsByProduct = 2;
    
    /**
     * 
     * @serial nPrimeOwnerCarry
     */
    //private static int nPrimeOwnerCarry = 10;
    
    /**
     * Minimum number of prime materials for restock
     * @serial nMinPrimeMaterialsForRestock
     */
    private static int nMinPrimeMaterialsForRestock = 10;
    
    /**
     * Maximum number of products that the owner can carry
     * @serial nMaxProductsCollect
     */
    private static int nMaxProductsCollect = 5;
    
    /**
     * Number of total products
     * @serial totalProducts
     */
    private static int totalProducts = 0;
    
    
    // PORTS
    /**
     * Register service host name
     * @serial RMIREGHOSTNAME
     */
    public static String RMIREGHOSTNAME = "localhost";
    
    /**
     * Register service port number
     * @serial RMIREGPORTNUMB
     */
    public static int RMIREGPORTNUMB = 22170;
    
    /**
     * Listening port of RMI Registry
     * @serial REGISTERPORT
     */
    public static final int REGISTERPORT = 22171;
    
    /**
     * Listening port of RMI Reposiroty
     * @serial REPOSITORYPORT
     */
    public static final int REPOSITORYPORT = 22172;
    
    /**
     * Listening port of RMI Shop
     * @serial SHOPPORT
     */
    public static final int SHOPPORT = 22173;
    
    /**
     * Listening port of RMI Factory
     * @serial FACTORYPORT
     */
    public static final int FACTORYPORT = 22174;
    
    /**
     * Listening port of RMI Storage
     * @serial STORAGEPORT
     */
    public static final int STORAGEPORT = 22175;    
    

//    /**
//     * Configurations Server Constructor with default values
//     * @param rmiRegHostName
//     * @param rmiRegPortNumb
//     */
//	public Configurations(String rmiRegHostName, int rmiRegPortNumb){
//        Configurations.RMIREGHOSTNAME = rmiRegHostName;
//        Configurations.RMIREGPORTNUMB = rmiRegPortNumb;
//        Configurations.fName = "log.txt";
//        Configurations.nCraftmans = 3;
//        Configurations.nCustomers = 3;
//        Configurations.nPrimeMaterialsInFactory = 10;
//        Configurations.nInitialProductsInShop = 10;
//        Configurations.nInitialPrimeMaterialsInStorage = 20;
//        Configurations.nPrimeMaterialsByProduct = 2;
//        Configurations.nMinPrimeMaterialsForRestock = 10;
//        Configurations.nMaxProductsCollect = 5;
//        Configurations.totalProducts = ((nPrimeMaterialsInFactory + nInitialPrimeMaterialsInStorage / nPrimeMaterialsByProduct) + nInitialProductsInShop);
//	}
//    
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
//        Configurations.RMIREGHOSTNAME = rmiRegHostName;
//        Configurations.RMIREGPORTNUMB = rmiRegPortNumb;
//        Configurations.fName = fName;
//        Configurations.nCraftmans = nCraftmans;
//        Configurations.nCustomers = nCustomers;
//        Configurations.nPrimeMaterialsInFactory = nPrimeMaterialsInFactory;
//        Configurations.nInitialProductsInShop = nInitialProductsInShop;
//        Configurations.nInitialPrimeMaterialsInStorage = nInitialPrimeMaterialsInStorage;
//        Configurations.nPrimeMaterialsByProduct = nPrimeMaterialsByProduct;
//        Configurations.nMinPrimeMaterialsForRestock = nMinPrimeMaterialsForRestock;
//        Configurations.nMaxProductsCollect = nMaxProductsCollect;
//        Configurations.totalProducts = ((nPrimeMaterialsInFactory + nInitialPrimeMaterialsInStorage / nPrimeMaterialsByProduct) + nInitialProductsInShop);
//    }

    
    /**
     * Configurations Server with default values
     * @param rmiRegHostName
     * @param rmiRegPortNumb
     */
	public static void setConfigurations(String rmiRegHostName, int rmiRegPortNumb){
        RMIREGHOSTNAME = rmiRegHostName;
        RMIREGPORTNUMB = rmiRegPortNumb;
        fName = "log.txt";
        nCraftmans = 3;
        nCustomers = 3;
        nPrimeMaterialsInFactory = 10;
        nInitialProductsInShop = 10;
        nInitialPrimeMaterialsInStorage = 20;
        nPrimeMaterialsByProduct = 2;
        nMinPrimeMaterialsForRestock = 10;
        nMaxProductsCollect = 5;
        totalProducts = ((nPrimeMaterialsInFactory + nInitialPrimeMaterialsInStorage / nPrimeMaterialsByProduct) + nInitialProductsInShop);
	}
    
    /**
     * Configurations Server with parameters
     * 
     * @param rmiRegHostName
     * @param rmiRegPortNumb
     * @param fName
     * @param nCraftmans
     * @param nCustomers
     * @param nPrimeMaterialsInFactory
     * @param nInitialProductsInShop
     * @param nInitialPrimeMaterialsInStorage
     * @param nPrimeMaterialsByProduct
     * @param nMinPrimeMaterialsForRestock
     * @param nMaxProductsCollect
     */
	public static void setConfigurations(String rmiRegHostName, int rmiRegPortNumb, String fName, int nCraftmans, int nCustomers, int nPrimeMaterialsInFactory, int nInitialProductsInShop, int nInitialPrimeMaterialsInStorage, int nPrimeMaterialsByProduct, int nMinPrimeMaterialsForRestock, int nMaxProductsCollect){
        Configurations.RMIREGHOSTNAME = rmiRegHostName;
        Configurations.RMIREGPORTNUMB = rmiRegPortNumb;
        Configurations.fName = fName;
        Configurations.nCraftmans = nCraftmans;
        Configurations.nCustomers = nCustomers;
        Configurations.nPrimeMaterialsInFactory = nPrimeMaterialsInFactory;
        Configurations.nInitialProductsInShop = nInitialProductsInShop;
        Configurations.nInitialPrimeMaterialsInStorage = nInitialPrimeMaterialsInStorage;
        Configurations.nPrimeMaterialsByProduct = nPrimeMaterialsByProduct;
        Configurations.nMinPrimeMaterialsForRestock = nMinPrimeMaterialsForRestock;
        Configurations.nMaxProductsCollect = nMaxProductsCollect;
        Configurations.totalProducts = ((nPrimeMaterialsInFactory + nInitialPrimeMaterialsInStorage / nPrimeMaterialsByProduct) + nInitialProductsInShop);
    }
    
    /**
     * Get logging file name.
     * @return log name
     */
    public static String getfName(){
        return fName;
    }

    /**
     * Logging file name.
     * @param fName file name
     */
//    @Override
//    public void setfName(String fName){
//        this.fName = fName;
//    }

    /**
     * Number of Craftmans.
     * @return number of craftmans
     */
    public static int getnCraftmans(){
        return nCraftmans;
    }

    /**
     * Number of Craftmans.
     * @param nCraftmans number of craftmans
     */
//    @Override
//    public void setnCraftmans(int nCraftmans){
//        this.nCraftmans = nCraftmans;
//    }

    /**
     * Number of Customers.
     * @return number of customers
     */
    public static int getnCustomers(){
        return nCustomers;
    }

    /**
     * Number of Customers.
     * @param nCustomers number of customers
     */
//    @Override
//    public void setnCustomers(int nCustomers){
//        this.nCustomers = nCustomers;
//    }

    /**
     * Num Prime Materials In Factory.
     * @return nPrimeMaterialsInFactory
     */
    public static int getnPrimeMaterialsInFactory(){
        return nPrimeMaterialsInFactory;
    }

    /**
     * Set num Prime Materials In Factory.
     * @param nPrimeMaterialsInFactory prime materials
     */
//    @Override
//    public void setnPrimeMaterialsInFactory(int nPrimeMaterialsInFactory){
//        this.nPrimeMaterialsInFactory = nPrimeMaterialsInFactory;
//    }

    /**
     * Get num Initial Products In Shop.
     * @return nInitialProductsInShop products in Shop
     */
    public static int getnInitialProductsInShop(){
        return nInitialProductsInShop;
    }

    /**
     * Set num Initial Products In Shop.
     * @param nInitialProductsInShop products in Shop
     */
//    @Override
//    public void setnInitialProductsInShop(int nInitialProductsInShop){
//        this.nInitialProductsInShop = nInitialProductsInShop;
//    }

    /**
     * Get num Initial Prime Materials In Storage.
     * @return nInitialPrimeMaterialsInStorage prime materials in Storage
     */
    public static int getnInitialPrimeMaterialsInStorage(){
        return nInitialPrimeMaterialsInStorage;
    }

    /**
     * Set num Initial Prime Materials In Storage.
     * @param nInitialPrimeMaterialsInStorage prime materials in Storage
     */
//    @Override
//    public void setnInitialPrimeMaterialsInStorage(int nInitialPrimeMaterialsInStorage){
//        this.nInitialPrimeMaterialsInStorage = nInitialPrimeMaterialsInStorage;
//    }

    /**
     * Get num Prime Materials By Product.
     * @return nPrimeMaterialsByProduct prime materials by product
     */
    public static int getnPrimeMaterialsByProduct(){
        return nPrimeMaterialsByProduct;
    }

    /**
     * Set num Prime Materials By Product.
     * @param nPrimeMaterialsByProduct prime materials by product
     */
//    @Override
//    public void setnPrimeMaterialsByProduct(int nPrimeMaterialsByProduct){
//        this.nPrimeMaterialsByProduct = nPrimeMaterialsByProduct;
//    }

    /**
     * Get num Max Products Collect.
     * @return nMaxProductsCollect Max Products Collect
     */
    public static int getnMaxProductsCollect(){
        return nMaxProductsCollect;
    }

    /**
     * Set num Max Products Collect.
     * @param nMaxProductsCollect Max Products Collect
     */
//    @Override
//    public void setnMaxProductsCollect(int nMaxProductsCollect){
//        this.nMaxProductsCollect = nMaxProductsCollect;
//    }

    /**
     * Get num Min Prime Materials For Restock.
     * @return nMinPrimeMaterialsForRestock Min Prime Materials For Restock
     */
    public static int getnMinPrimeMaterialsForRestock(){
        return nMinPrimeMaterialsForRestock;
    }

    /**
     * Set num Min Prime Materials For Restock.
     * @param nMinPrimeMaterialsForRestock Min Prime Materials For Restock
     */
//    @Override
//    public void setnMinPrimeMaterialsForRestock(int nMinPrimeMaterialsForRestock){
//        this.nMinPrimeMaterialsForRestock = nMinPrimeMaterialsForRestock;
//    }

    /**
     * Get total Products.
     * @return totalProducts total Products
     */
    public static int gettotalProducts(){
        return totalProducts;
    }

    /**
     * Set total Products.
     * @param totalProducts total Products
     */
//    @Override
//    public void settotalProducts(int totalProducts){
//        this.totalProducts = totalProducts;
//    }
    
}
