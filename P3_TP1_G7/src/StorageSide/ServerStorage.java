package StorageSide;

import java.util.Scanner;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 3.0
 */
public class ServerStorage {
    public static void main(String[] args){
        /* get location of the registry service */
        Scanner in = new Scanner(System.in);
        String rmiRegHostName;
        int rmiRegPortNumb;

        System.out.print("Nome do nó de processamento onde está localizado o serviço de registo? ");
        rmiRegHostName = in.nextLine();
        System.out.print("Número do port de escuta do serviço de registo? ");
        rmiRegPortNumb = in.nextInt();
    }
}
