/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OAndH;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class Admin {

    public static ArrayList<String[]> manager = new ArrayList<>();
    public static ArrayList<String[]> employee = new ArrayList<>();
    public static ArrayList<String[]> holiday = new ArrayList<>();
    public static ArrayList<String[]> attendancearray = new ArrayList<>();
    public static File mfile = new File("manager.txt");
    public static File hfile = new File("holiday.txt");
    public static File efile = new File("employee.txt");
    Scanner in = new Scanner(System.in);
    public static String managerid;
    public static String employeeid;

    public boolean idpassword() throws FileNotFoundException {
        Scanner scanner = new Scanner(mfile);
        boolean found = true;
        if (!scanner.hasNextLine()) {
            return false;
        }
        System.out.print("Enter Id :");
        String managerIdInput = in.nextLine();
        System.out.print("Enter password :");
        String managerPasswordInput = in.nextLine();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] array = line.split(" ");
            if (array[0].equals(managerIdInput) && array[2].equals(managerPasswordInput)) {
                managerid = array[0];
                //  System.out.println(array[0]);
                found = true;

                break;
            } else {
                found = false;
            }
        }
        if (found) {
            return true;
        } else {
            return false;
        }
    }

    public void mftoma() throws FileNotFoundException, IOException {
        if (!mfile.exists()) {
            mfile.createNewFile();
        }
        Scanner scanner = new Scanner(mfile);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] array = line.split(" ");
            manager.add(array);
        }

    }

    public void add() throws IOException {
        FileOutputStream mos = new FileOutputStream(mfile, true);
        if (!mfile.exists()) {
            mfile.createNewFile();
        }
        System.out.print("Enter ID: "); //Id index 0
        String managerid = in.nextLine();
        this.managerid = managerid.replace(" ", "-");
        System.out.print("Enter Name: "); //name index 1
        String managername = in.nextLine().replace(" ", "-");
        System.out.print("Enter Password: "); //password index 2
        String managerpassword = in.nextLine().replace(" ", "-");
        System.out.print("Enter Email: "); //Emil index 3
        String manageremail = in.nextLine().replace(" ", "-");
        System.out.print("Enter Phone Number: "); //phone index 4
        String managerphone = in.nextLine().replace(" ", "-");
        System.out.print("Enter Status: "); //status index 5
        String managerstatus = in.nextLine().replace(" ", "-");
        String managerarray[] = {managerid, managername, managerpassword, manageremail, managerphone, managerstatus};
        for (int i = 0; i < managerarray.length; i++) {
            mos.write(managerarray[i].getBytes());
            mos.write(" ".getBytes());
        }
        mos.write("\n".getBytes());
        mos.flush();
        mos.close();
        manager.add(managerarray);
        System.out.println("the manger has been successfully added...");

    }//method

    public void update() throws FileNotFoundException, IOException {
        Scanner scanner = new Scanner(mfile);
        FileOutputStream mos = new FileOutputStream(mfile, true);
        if (!mfile.exists()) {
            mfile.createNewFile();
        }
        boolean found = true;
        System.out.println("update manager");
        System.out.print("Enter manager Id :");
        String managerid = in.nextLine();

        for (int i = 0; i < manager.size(); i++) {
            String[] array = manager.get(i);
            if (array[0].equals(managerid)) {
                System.out.print("Enter new password :");
                String managerpassword = in.nextLine().replace(" ", "-");
                array[2] = managerpassword;
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String parts[] = line.split(" ");
                    if (parts[0].equals(managerid)) {
                        parts[2] = managerpassword;
                        mos.flush();
                        mos.close();
                        if (mfile.exists()) {
                            mos = new FileOutputStream(mfile, false);
                            mos = new FileOutputStream(mfile, true);
                            for (int j = 0; j < manager.size(); j++) {
                                String[] array2 = manager.get(j);
                                for (int y = 0; y < array2.length; y++) {
                                    mos.write(array2[y].getBytes());
                                    mos.write(" ".getBytes());
                                }
                                mos.write("\n".getBytes());
                            }
                        }
                    }
                }
                mos.flush();
                mos.close();
                System.out.println("the manager password was successfully updated.");
                found = true;
                break;
            } else {
                found = false;
            }
        }
        if (!found) {
            System.out.println("this id is not found ");
        }

    }//method

    public void delete() throws FileNotFoundException, IOException {
        //     Scanner scanner = new Scanner(mfile);
        FileOutputStream mos = new FileOutputStream(mfile, true);
        boolean found = true;
        if (manager.isEmpty()) {
            System.out.println("There Are No Managers");
            return;
        }
        System.out.println(">>>>> Delete Manager <<<<<");
        System.out.print("Enter ID: ");
        String managerid = in.nextLine();
        for (int i = 0; i < manager.size(); i++) {
            String[] array = manager.get(i);
            if (array[0].equals(managerid)) {
                manager.remove(i);
                if (mfile.exists()) {
                    mos = new FileOutputStream(mfile, false);
                    mos = new FileOutputStream(mfile, true);
                    for (int j = 0; j < manager.size(); j++) {
                        String[] array2 = manager.get(j);
                        for (int y = 0; y < array2.length; y++) {
                            mos.write(array2[y].getBytes());
                            mos.write(" ".getBytes());
                        }
                        mos.write("\n".getBytes());
                    }
                }
                mos.flush();
                mos.close();

                System.out.println("the manager was successfully deleted.");
                found = true;
                break;
            } else {
                found = false;
            }
        }
        if (!found) {
            System.out.println("this id does not found ");
        }

    } //method

    public void search() throws FileNotFoundException {
        boolean found = true;
        Scanner scanner = new Scanner(mfile);
        System.out.println(">>>>> search about Manager <<<<<");
        System.out.print("Enter ID: ");
        String managerid = in.nextLine();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String parts[] = line.split(" ");
            if (parts[0].equals(managerid)) {
                found = true;
                System.out.println("Name: " + parts[1] + " || Status: " + parts[5]);
            }
            if (!found) {
                System.out.println("This ID Does Not Exist");
            }
        }

    }//method

    public void report() throws FileNotFoundException {
        Scanner scanner = new Scanner(mfile);
        boolean found = true;
        String checkin = "";
        String checkout = "";
        System.out.println(">>>>> Report About Manager <<<<<");
        System.out.print("Enter Manager ID: ");
        String managerid = in.nextLine();
        for (int j = 0; j < attendancearray.size(); j++) {
            String[] array2 = attendancearray.get(j);
            if (array2[0].equals(managerid)) {
                checkin = array2[1];
                checkout = array2[2];
            }
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String array[] = line.split(" ");
            if (array[0].equals(managerid)) {
                found = true;
                System.out.println("Manager ID: " + array[0] + " || Manager Name: " + array[1]
                        + " || Manager Email: " + array[2] + " || Manager Phone: " + array[3]
                        + " || Manager Type: " + array[4] + " || Manager Status: " + array[5]
                        + " || CheckIn: " + checkin + " || CheckOut: " + checkout);
            }

        }

        if (!found) {
            System.out.println("this id is not found ");
        }
    } //method

    public void reportAll() throws FileNotFoundException, IOException {
                   
        Scanner scanner = new Scanner(mfile);
        String checkin = "";
        String checkout = "";
        System.out.println(">>>>>> Report about all Manager <<<<<");
      
        for (int j = 0; j < attendancearray.size(); j++) {
            String[] array2 = attendancearray.get(j);
            
            if (array2[0].equals(managerid)) {
                checkin = array2[1];
                checkout = array2[2];
            }
        }

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String array[] = line.split(" ");
                System.out.println("Manager ID: " + array[0] + " || Manager Name: " + array[1]
                        + " || Manager Email: " + array[2] + " || Manager Phone: " + array[3]
                        + " || Manager Type: " + array[4] + " || Manager Status: " + array[5]
                        + " || CheckIn: " + checkin + " || CheckOut: " + checkout);
            }
        }
    
    //        System.out.println(">>>>>> choose the view of data on the screen <<<<<<");
    //        System.out.println("1-Ascending order by name");
    //        System.out.println("2-Descending order by name");
    //        String order = in.nextLine();
    //        switch (order) {
    //            case "1":
    //                String[] names = new String[manager.size()];
    //                String[][] data = new String[manager.size()][3];
    //                for (int i = 0; i < manager.size(); i++) {
    //                    data[i] = manager.get(i);
    //                    names[i] = data[i][1];
    //                }
    //                Arrays.sort(names);
    //                for (int i = 0; i < names.length; i++) {
    //                    for (int j = 0; j < data.length; j++) {
    //                        if (data[j][1].equals(names[i])) {
    //                            System.out.println(Arrays.toString(data[j]));
    //                        }
    //                    }
    //                }
    //                break;
    //            case "2":
    //                String[] names2 = new String[manager.size()];
    //                String[][] data2 = new String[manager.size()][3];
    //                for (int i = 0; i < manager.size(); i++) {
    //                    data2[i] = manager.get(i);
    //                    names2[i] = data2[i][1];
    //                }
    //                Arrays.sort(names2, Collections.reverseOrder());
    //                for (int i = 0; i < names2.length; i++) {
    //                    for (int j = 0; j < data2.length; j++) {
    //                        if (data2[j][1].equals(names2[i])) {
    //                            System.out.println(Arrays.toString(data2[j]));
    //                        }
    //                    }
    //                }
    //                break;
    //            default:
    //                System.out.println("wrong choice, choose a correct number (1, 2)");
    //                break;
    //        }
    //    }

    public void holiday() throws IOException {
        Scanner scanner = new Scanner(hfile);
        FileOutputStream hos = new FileOutputStream(hfile, true);
        boolean found = true;
        boolean da = true;
        while (da) {
            System.out.println(">>>>> Holiday Requests <<<<<");
            System.out.println("1- View Holiday Requests\n2- Accept Holiday \n3- Reject Holiday\n4- Exit");
            int choice = in.nextInt();
            in.nextLine();
            switch (choice) {
                case 1:
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        String[] array = line.split(" ");
                        System.out.println("ID: " + array[0] + " || Name: " + array[1] + " || Reason: " + array[2]
                                + " || Details: " + array[3] + " || Date: " + array[4]);
                    }

                    break;
                case 2:
                    System.out.println(">>>>> Accept Holiday <<<<<");
                    System.out.print("Enter ID: ");
                    String holidayaccept = in.nextLine();
                    for (int i = 0; i < holiday.size(); i++) {
                        String[] array = holiday.get(i);
                        if (holidayaccept.equals(array[0])) {
                            array[5] = "1";
                            System.out.println("Approved Successfully");
                            found = true;
                            if (mfile.exists()) {
                                hos = new FileOutputStream(hfile, false);
                                hos = new FileOutputStream(hfile, true);
                                for (int j = 0; j < holiday.size(); j++) {
                                    String[] array2 = holiday.get(j);
                                    for (int y = 0; y < array2.length; y++) {
                                        hos.write(array2[y].getBytes());
                                        hos.write(" ".getBytes());
                                    }
                                    hos.write("\n".getBytes());
                                }
                            }
                            hos.flush();
                            hos.close();
                            break;
                        } else {
                            found = false;
                        }
                    }
                    if (!found) {
                        System.out.println("ID not found");
                    }
                    break;
                case 3:
                    System.out.println(">>>>> Reject Holiday <<<<<");
                    System.out.print("Enter ID: ");
                    String holidayreject = in.nextLine();
                    for (int i = 0; i < holiday.size(); i++) {
                        String[] array = holiday.get(i);
                        if (holidayreject.equals(array[0])) {
                            array[5] = "2";
                            System.out.println("The Request Was Rejected");
                            found = true;
                            if (mfile.exists()) {
                                hos = new FileOutputStream(hfile, false);
                                hos = new FileOutputStream(hfile, true);
                                for (int j = 0; j < holiday.size(); j++) {
                                    String[] array2 = holiday.get(j);
                                    for (int y = 0; y < array2.length; y++) {
                                        hos.write(array2[y].getBytes());
                                        hos.write(" ".getBytes());
                                    }
                                    hos.write("\n".getBytes());
                                }
                            }
                            hos.flush();
                            hos.close();
                            break;
                        } else {
                            found = false;
                        }
                    }
                    if (!found) {
                        System.out.println("ID not found");
                    }
                    break;
                case 4:
                    da = false;
                    break;

            }
        }
    }

    public void DeactivateAndActivate() throws FileNotFoundException, IOException {
        FileOutputStream mos = new FileOutputStream(mfile, true);
        boolean found = true;
        boolean da = true;
        while (da) {
            System.out.println(">>>>> Deactivate And Activate manager <<<<<");
            System.out.println("1 -Activate\n2 -Deactivate\n3 -Exit");
            int choice = in.nextInt();
            in.nextLine();
            switch (choice) {
                case 1:
                    System.out.println(">>>>> Activate manager <<<<<");
                    System.out.print("Enter ID: ");
                    String manageractivate = in.nextLine();
                    for (int i = 0; i < manager.size(); i++) {
                        String[] array = manager.get(i);
                        if (manageractivate.equals(array[0])) {
                            array[5] = "1";
                            System.out.println("The Account Has Been Activated");
                            found = true;
                            if (mfile.exists()) {
                                mos = new FileOutputStream(mfile, false);
                                mos = new FileOutputStream(mfile, true);
                                for (int j = 0; j < manager.size(); j++) {
                                    String[] array2 = manager.get(j);
                                    for (int y = 0; y < array2.length; y++) {
                                        mos.write(array2[y].getBytes());
                                        mos.write(" ".getBytes());
                                    }
                                    mos.write("\n".getBytes());
                                }
                            }
                            mos.flush();
                            mos.close();
                            break;
                        } else {
                            found = false;
                        }
                    }
                    if (!found) {
                        System.out.println("ID not found");
                    }

                case 2:
                    System.out.println(">>>>> Deactivate manager <<<<<");
                    System.out.print("Enter ID: ");
                    String manageractivated = in.nextLine();
                    for (int i = 0; i < manager.size(); i++) {
                        String[] array = manager.get(i);
                        if (manageractivated.equals(array[0])) {
                            array[5] = "0";
                            System.out.println("The Account Has Been Deactivated");
                            found = true;
                            if (mfile.exists()) {
                                mos = new FileOutputStream(mfile, false);
                                mos = new FileOutputStream(mfile, true);
                                for (int j = 0; j < manager.size(); j++) {
                                    String[] array2 = manager.get(j);
                                    for (int y = 0; y < array2.length; y++) {
                                        mos.write(array2[y].getBytes());
                                        mos.write(" ".getBytes());
                                    }
                                    mos.write("\n".getBytes());
                                }
                            }
                            mos.flush();
                            mos.close();

                        } else {
                            found = false;
                        }
                    }
                    if (!found) {
                        System.out.println("ID not found");
                    }

                case 3:
                    da = false;
                    break;
            }
        }
    } //method

    public void getCount() {
        System.out.println(">>>>> Get Manager And Employee Count <<<<<");
        System.out.println("Managers: " + manager.size());
        System.out.println("Employees: " + employee.size());
    }//method
}
