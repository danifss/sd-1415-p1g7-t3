package Registry;

/**
 *
 * @author silva
 */
public class OldConfigurations {
    
    /**
     * Logging file name
     * @serial fName
     */
	private static String fName; // = "log.txt";
    
    /**
     * Number of Craftmans.
     * @serial nCraftmans
     */
	private static int nCraftmans; // = 3;
    
    /**
     * Number of Customers
     * @serial nCustomers
     */
    private static int nCustomers; // = 3;
    
    /**
     * Initial number of prime materials in the Factory
     * @serial nPrimeMaterialsInFactory
     */
    private static int nPrimeMaterialsInFactory; // = 10;
    
    /**
     * Initial number of products in the Shop
     * @serial nInitialProductsInShop
     */
    private static int nInitialProductsInShop; // = 10;
    
    /**
     * Initial number of prime materials in the Storage
     * @serial nInitialPrimeMaterialsInStorage
     */
    private static int nInitialPrimeMaterialsInStorage; // = 20;
    
    /**
     * Prime materials needed per product
     * @serial nPrimeMaterialsByProduct
     */
    private static int nPrimeMaterialsByProduct; // = 1;
    
    /**
     * 
     * @serial nPrimeOwnerCarry
     */
    //private static int nPrimeOwnerCarry = 10;
    
    /**
     * Minimum number of prime materials for restock
     * @serial nMinPrimeMaterialsForRestock
     */
    private static int nMinPrimeMaterialsForRestock; // = 10;
    
    /**
     * Maximum number of products that the owner can carry
     * @serial nMaxProductsCollect
     */
    private static int nMaxProductsCollect; // = 5;
    
    /**
     * Number of total products
     * @serial totalProducts
     */
    private static int totalProducts;
    
    
    // PORTS
    /**
     * Register service host name
     * @serial RMIREGHOSTNAME
     */
    private static String RMIREGHOSTNAME = "localhost";
    
    /**
     * Register service port number
     * @serial RMIREGPORTNUMB
     */
    private static int RMIREGPORTNUMB = 22170;
    
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

    
    
    
    public static String getfName(){
        return fName;
    }

    public static int getnCraftmans(){
        return nCraftmans;
    }

    public static int getnCustomers(){
        return nCustomers;
    }

    public static int getnPrimeMaterialsInFactory(){
        return nPrimeMaterialsInFactory;
    }

    public static int getnInitialProductsInShop(){
        return nInitialProductsInShop;
    }

    public static int getnInitialPrimeMaterialsInStorage(){
        return nInitialPrimeMaterialsInStorage;
    }

    public static int getnPrimeMaterialsByProduct(){
        return nPrimeMaterialsByProduct;
    }

    public static int getnMinPrimeMaterialsForRestock(){
        return nMinPrimeMaterialsForRestock;
    }

    public static int getnMaxProductsCollect(){
        return nMaxProductsCollect;
    }

    public static int getTotalProducts(){
        return totalProducts;
    }

    public static String getRMIREGHOSTNAME(){
        return RMIREGHOSTNAME;
    }

    public static int getRMIREGPORTNUMB(){
        return RMIREGPORTNUMB;
    }
    
    

    
    
//    
//    public static void setfName(String fName){
//        Configurations.fName = fName;
//    }
//
//    public static void setnCraftmans(int nCraftmans){
//        Configurations.nCraftmans = nCraftmans;
//    }
//
//    public static void setnCustomers(int nCustomers){
//        Configurations.nCustomers = nCustomers;
//    }
//
//    public static void setnPrimeMaterialsInFactory(int nPrimeMaterialsInFactory){
//        Configurations.nPrimeMaterialsInFactory = nPrimeMaterialsInFactory;
//    }
//
//    public static void setnInitialProductsInShop(int nInitialProductsInShop){
//        Configurations.nInitialProductsInShop = nInitialProductsInShop;
//    }
//
//    public static void setnInitialPrimeMaterialsInStorage(int nInitialPrimeMaterialsInStorage){
//        Configurations.nInitialPrimeMaterialsInStorage = nInitialPrimeMaterialsInStorage;
//    }
//
//    public static void setnPrimeMaterialsByProduct(int nPrimeMaterialsByProduct){
//        Configurations.nPrimeMaterialsByProduct = nPrimeMaterialsByProduct;
//    }
//
//    public static void setnMinPrimeMaterialsForRestock(int nMinPrimeMaterialsForRestock){
//        Configurations.nMinPrimeMaterialsForRestock = nMinPrimeMaterialsForRestock;
//    }
//
//    public static void setnMaxProductsCollect(int nMaxProductsCollect){
//        Configurations.nMaxProductsCollect = nMaxProductsCollect;
//    }
//
//    public static void setTotalProducts(int totalProducts){
//        Configurations.totalProducts = totalProducts;
//    }
//
//    public static void setRMIREGHOSTNAME(String RMIREGHOSTNAME){
//        Configurations.RMIREGHOSTNAME = RMIREGHOSTNAME;
//    }
//
//    public static void setRMIREGPORTNUMB(int RMIREGPORTNUMB){
//        Configurations.RMIREGPORTNUMB = RMIREGPORTNUMB;
//    }
//    
}
