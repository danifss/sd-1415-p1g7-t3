package CraftmanSide;

import java.util.Scanner;


/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 3.0
 */
public class CraftmanMain {
    public static void main(String[] args){
        /**
         * TODO: obter o objeto Repository, o objeto Shop e o objeto Factory por RMI usando o 
         * host e o port que vem por argumento.
         * 
         * Iniciar os craftmans.
         */
        
        /* get location of the generic registry service */
        Scanner in = new Scanner(System.in);
        String rmiRegHostName;
        int rmiRegPortNumb;

        System.out.print("Nome do nó de processamento onde está localizado o serviço de registo? ");
        rmiRegHostName = in.nextLine();
        System.out.print("Número do port de escuta do serviço de registo? ");
        rmiRegPortNumb = in.nextInt();
    }
    
}
