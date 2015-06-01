package Registry;

/**
 *
 * @author silva
 */
public class Config {
    
    /**
     * Logging file name
     * @serial fName
     */
	public static String fName = "log.txt";
    
    /**
     * Number of Craftmans.
     * @serial nCraftmans
     */
	private static int nCraftmans;
    
    /**
     * Number of Customers
     * @serial nCustomers
     */
    private static int nCustomers;
    
    /**
     * Initial number of prime materials in the Factory
     * @serial nPrimeMaterialsInFactory
     */
    private static int nPrimeMaterialsInFactory;
    
    /**
     * Initial number of products in the Shop
     * @serial nInitialProductsInShop
     */
    private static int nInitialProductsInShop;
    
    /**
     * Initial number of prime materials in the Storage
     * @serial nInitialPrimeMaterialsInStorage
     */
    private static int nInitialPrimeMaterialsInStorage;
    
    /**
     * Prime materials needed per product
     * @serial nPrimeMaterialsByProduct
     */
    private static int nPrimeMaterialsByProduct;
    
    /**
     * 
     * @serial nPrimeOwnerCarry
     */
    //private static int nPrimeOwnerCarry = 10;
    
    /**
     * Minimum number of prime materials for restock
     * @serial nMinPrimeMaterialsForRestock
     */
    private static int nMinPrimeMaterialsForRestock;
    
    /**
     * Maximum number of products that the owner can carry
     * @serial nMaxProductsCollect
     */
    private static int nMaxProductsCollect;
    
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
    public static String RMIREGHOSTNAME; // localhost
    
    /**
     * Register service port number
     * @serial RMIREGPORTNUMB
     */
    public static int RMIREGPORTNUMB; // 22170
    
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

    public Config(){
        fName = "log.txt";
    }
    
    
}
