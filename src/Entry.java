import java.sql.*;
import java.util.Scanner;

public class Entry {
    private static final String sql = "SELECT * FROM \"champion_LOL\"";

    public static InfoCham[] getListCham(InfoCham[] infoChams, ConnectSQL connectSQL){
        ResultSet result = connectSQL.getResult(sql);
        while (true)
        {
            try {
                if (!result.next()) break;
                int i = result.getInt("id");
                infoChams[i] = new InfoCham(i, result.getString("champion"), result.getString("classes"),
                        result.getString("position"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return infoChams;
    }
    public static void DisplayListCham(InfoCham[] infoChams)
    {
        for (int i = 1; i < infoChams.length; i++)
        {
            if (i <= 10) System.out.printf("%d.%-21s", i, infoChams[i].getChampion());
            else if (i > 100) System.out.printf("%d.%-19s", i, infoChams[i].getChampion());
            else System.out.printf("%d.%-20s", i, infoChams[i].getChampion());

            if (i % 5 == 0) System.out.println();
        }
        System.out.println();
    }
    public static InfoCham FindCham(InfoCham[] infoCham,String champion)
    {
        for (int i = 1; i < infoCham.length; i++)
            if (infoCham[i].getChampion().equalsIgnoreCase(champion))
                return infoCham[i];
        return null;
    }
    public static void filterWithPosition(InfoCham[] infoChams, String position)
    {
        int count = 0;
        System.out.printf("Name%-25sPosition\n", "");
        System.out.println("-------------------------------------------");
        for (int i = 1; i < infoChams.length; i++)
        {
            if (infoChams[i].getPosition().toLowerCase().contains(position.toLowerCase()))
            {
                System.out.printf("%-30s%s\n",infoChams[i].getChampion(), infoChams[i].getPosition());
                count++;
            }
        }
        if (count == 0) System.out.println("There are no champions with " + position + " positions ");
    }
    public static void filterWithClasses(InfoCham[] infoChams, String classes)
    {
        int count = 0;
        System.out.printf("Name%-25sClasses\n", "");
        System.out.println("-------------------------------------------");
        for (int i = 1; i < infoChams.length; i++)
        {
            if (infoChams[i].getClasses().toLowerCase().contains(classes.toLowerCase()))
            {
                System.out.printf("%-30s%s\n",infoChams[i].getChampion(), infoChams[i].getClasses());
                count++;
            }
        }
        if (count == 0) System.out.println("There are no champions with " + classes + " classes ");
    }
    public static void filterWithLettersOfName(InfoCham[] infoChams, String f)
    {
        for (int i = 1; i < infoChams.length; i++)
        {
            if (infoChams[i].getChampion().toLowerCase().startsWith(f.toLowerCase()))
            {
                System.out.printf("%s\n",infoChams[i].getChampion());
            }
        }
    }
    public static void fullListChamInfo(InfoCham[] infoChams)
    {
        System.out.printf("ID%-8sChampion%-12sClasses%-18sPosition\n", "", "", "");
        System.out.println("---------------------------------------------------------------------");

        for (int i = 1; i < infoChams.length; i++)
        {
            System.out.printf("%-10d%-20s%-24s%s\n", i,infoChams[i].getChampion(), infoChams[i].getClasses(), infoChams[i].getPosition());
            System.out.println("---------------------------------------------------------------------");
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ConnectSQL connectSQL = new ConnectSQL();
        int n = connectSQL.getLength(sql);
        InfoCham[] infoChams = new InfoCham[n + 1];
        infoChams = getListCham(infoChams, connectSQL);

        while (true)
        {
            System.out.println("\n");
            System.out.println("-------------------------------------------");
            System.out.println("\t\tLEAGUA OF LEGENDS");
            System.out.println("1. List Champions and view information of champion");
            System.out.println("2. Find Champion");
            System.out.println("3. Filter List Champion");
            System.out.println("4. Full list champion infomation (ID, Champion, Classes, Position)");
            System.out.println("0. Stop");
            System.out.println("-------------------------------------------");

            System.out.print("Input choice: ");
            int choice = sc.nextInt();
            if (choice == 1)
            {
                DisplayListCham(infoChams);
                char c;
                do {
                    System.out.print("Do you want to know the information of any champion? Input (Y or N): ");
                    c = sc.next().charAt(0);
                    if (c == 'N') break;
                    System.out.print("Input id a champion you want to view information: ");
                    int i = sc.nextInt();
                    while (i < 1 || i > n)
                    {
                        System.out.println("Id the champion you entered does not exist!");
                        System.out.print("Please input agian: ");
                        i = sc.nextInt();
                    }
                    System.out.println("-------------------------------------------");
                    infoChams[i].display();
                    System.out.println("-------------------------------------------");
                } while (c == 'Y' || c == 'y');
            }
            if (choice == 2)
            {
                sc.nextLine();
                System.out.print("Input the champion's name: ");
                String ChamName = sc.nextLine();
                if (FindCham(infoChams, ChamName) == null)
                    System.out.println("Champion does not exist or input wrong name");
                else FindCham(infoChams, ChamName).display();
            }
            if (choice == 3)
            {
                System.out.println("\t1.Filter with position");
                System.out.println("\t2.Filter with classes ");
                System.out.println("\t3.Filter with characters of name");
                System.out.println("\tInput choice: ");
                choice = sc.nextInt();
                if (choice == 1)
                {
                    sc.nextLine();
                    System.out.println("Position: Top, Middle, Jungle, Bottom, Support");
                    System.out.print("Input position: ");
                    String position = sc.nextLine();
                    filterWithPosition(infoChams, position);
                }
                if (choice == 2)
                {
                    sc.nextLine();
                    System.out.println("Classes: Juggernaut, Burts, Assassin, Marksman, Vanguard, Battlemage,");
                    System.out.println("Specialist, Catcher, Warden, Diver, Skirmisher, Artillery");
                    System.out.print("Input classes: ");
                    String classes = sc.nextLine();
                    filterWithClasses(infoChams, classes);
                }
                if (choice == 3)
                {
                    sc.nextLine();
                    System.out.print("Input character: ");
                    String ch = sc.nextLine();
                    filterWithLettersOfName(infoChams, ch);
                }
            }
            if (choice == 4)
            {
                fullListChamInfo(infoChams);
            }
            if (choice == 0)
            {
                connectSQL.disconect();
                System.out.println("Stop program!");
                break;
            }
        }

    }
}
