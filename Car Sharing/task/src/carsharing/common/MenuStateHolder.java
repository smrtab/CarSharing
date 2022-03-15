package carsharing.common;

import carsharing.data.entities.Company;
import carsharing.data.menus.MainMenu;
import carsharing.data.menus.Menu;

import java.sql.SQLException;

public class MenuStateHolder {

    private Menu selectedMenu;

    public Menu getSelectedMenu() {
        return selectedMenu;
    }

    public void setSelectedMenu(Menu selectedMenu) {
        this.selectedMenu = selectedMenu;
    }

    public void changeState(int point) throws CarSharingException, SQLException {

        if (!selectedMenu.validateSelectedPoint(point)) {
            throw new CarSharingException("The menu selected is incorrect");
        }

        setSelectedMenu(
            selectedMenu.handleClick(point)
        );
    }
}
