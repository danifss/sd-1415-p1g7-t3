./set_rmiregistry_alt.sh 22170 &
sleep 3
cd dir_registry ./registry_com_alt.sh localhost 22170 &
sleep 3
cd .. cd dir_repositorySide ./repositorySide_com_alt.sh &
sleep 2
cd .. cd dir_shopSide ./shopSide_com_alt.sh &
sleep 2
cd .. cd dir_factorySide ./factorySide_com_alt.sh &
sleep 2
cd .. cd dir_storageSide ./storageSide_com_alt.sh &
sleep 2
cd .. cd dir_ownerSide ./ownerSide_com_alt.sh &
sleep 2
cd .. cd dir_craftmanSide ./craftmanSide_com_alt.sh &
sleep 2
cd .. cd dir_customerSide ./customerSide_com_alt.sh
