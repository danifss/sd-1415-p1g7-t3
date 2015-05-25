javac   Interfaces/*.java 
        Registry/*.java
        RepositorySide/*.java
        ShopSide/*.java
        FactorySide/*.java
        StorageSide/*.java
        CraftmanSide/*.java
        CustomerSide/*.java
        OwnerSide/*.java

cp Interfaces/RegisterInterface.class dir_registry/interfaces/
cp Registry/*.class dir_registry/registry/
cp Interfaces/*.class dir_*Side/interfaces/
cp RepositorySide/*.class dir_repositorySide/repositorySide/
cp ShopSide/*.class dir_shopSide/shopSide/
cp FactorySide/*.class dir_factorySide/factorySide/
cp StorageSide/*.class dir_storageSide/storageSide/
cp CraftmanSide/*.class dir_craftmanSide/craftmanSide/
cp CustomerSide/*.class dir_customerSide/customerSide/
cp OwnerSide/*.class dir_ownerSide/ownerSide/

cp Interfaces/*.class ./Public/classes/interfaces
cp CraftmanSide/*.class ./Public/classes/craftmanSide
cp CustomerSide/*.class ./Public/classes/customerSide
cp OwnerSide/*.class ./Public/classes/ownerSide
