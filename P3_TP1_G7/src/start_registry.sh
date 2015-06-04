#killall rmiregistry &
./set_rmiregistry_alt.sh 22170 &&
#sleep 3
cd dir_registry ./registry_com_alt.sh localhost 22170
