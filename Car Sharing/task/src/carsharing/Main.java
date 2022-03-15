package carsharing;

import carsharing.common.CarSharingDBManager;
import carsharing.common.CarSharingException;
import carsharing.common.MenuService;
import carsharing.data.menus.MainMenu;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            CarSharingDBManager carSharingDBManagerInstance
                = CarSharingDBManager.getInstance(args);
            carSharingDBManagerInstance.init();

            MenuService menuService = new MenuService();
            menuService
                .getMenuStateHolder()
                .setSelectedMenu(new MainMenu());

            while (!menuService.isExitClicked()) {
                System.out.println(menuService);
                menuService.clicked(
                    scanner.nextInt()
                );
            }
        } catch (CarSharingException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}