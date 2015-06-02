rm -v -r */*.class
#rm -v -r dir_*/*/*.class
javac Interfaces/*.java 
javac Registry/*.java
javac RepositorySide/*.java
javac ShopSide/*.java
javac FactorySide/*.java
javac StorageSide/*.java
javac CraftmanSide/*.java
javac CustomerSide/*.java
javac OwnerSide/*.java
echo "Compilation done."

echo "Copying Registry classes."
#cp -v -r Registry/*.class dir_*/Registry/
cp Registry/*.class dir_registry/Registry/
cp Registry/*.class dir_repositorySide/Registry/
cp Registry/*.class dir_shopSide/Registry/
cp Registry/*.class dir_factorySide/Registry/
cp Registry/*.class dir_storageSide/Registry/
cp Registry/*.class dir_ownerSide/Registry/
cp Registry/*.class dir_craftmanSide/Registry/
cp Registry/*.class dir_customerSide/Registry/
echo "done"

echo "Copying Interfaces classes."
#cp Interfaces/*.class dir_*Side/Interfaces/
cp Interfaces/*.class dir_registry/Interfaces/
cp Interfaces/*.class dir_repositorySide/Interfaces/
cp Interfaces/*.class dir_shopSide/Interfaces/
cp Interfaces/*.class dir_factorySide/Interfaces/
cp Interfaces/*.class dir_storageSide/Interfaces/
cp Interfaces/*.class dir_craftmanSide/Interfaces/
cp Interfaces/*.class dir_customerSide/Interfaces/
cp Interfaces/*.class dir_ownerSide/Interfaces/
echo "done"

echo "Copying Clients classes."
cp RepositorySide/*.class dir_repositorySide/RepositorySide/
cp ShopSide/*.class dir_shopSide/ShopSide/
cp FactorySide/*.class dir_factorySide/FactorySide/
cp StorageSide/*.class dir_storageSide/StorageSide/
cp CraftmanSide/*.class dir_craftmanSide/CraftmanSide/
cp CustomerSide/*.class dir_customerSide/CustomerSide/
cp OwnerSide/*.class dir_ownerSide/OwnerSide/
echo "done"

#cp Interfaces/*.class ./Public/classes/interfaces
#cp CraftmanSide/*.class ./Public/classes/craftmanSide
#cp CustomerSide/*.class ./Public/classes/customerSide
#cp OwnerSide/*.class ./Public/classes/ownerSide

echo "All done."
