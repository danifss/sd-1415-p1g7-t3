#sleep 3
cd dir_repositorySide ./repositorySide_com_alt.sh &
echo "Repository started."
sleep 2
cd dir_shopSide ./shopSide_com_alt.sh &
echo "Shop started."
sleep 2
cd dir_factorySide ./factorySide_com_alt.sh &
echo "Factory started."
sleep 2
cd dir_storageSide ./storageSide_com_alt.sh &
echo "Storage started."
sleep 2
cd dir_ownerSide ./ownerSide_com_alt.sh &
echo "Owner started."
sleep 2
cd dir_craftmanSide ./craftmanSide_com_alt.sh &
echo "Craftmans started."
sleep 2
cd dir_customerSide ./customerSide_com_alt.sh &
echo "Customers started."
