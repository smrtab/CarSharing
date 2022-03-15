package carsharing.common;

import carsharing.data.menus.*;

import java.sql.SQLException;

public class MenuService {

    private MenuStateHolder menuStateHolder = new MenuStateHolder();

    public String toString() {
        return "\n" + menuStateHolder.getSelectedMenu().toString();
    }

    public void clicked(int menuPoint) throws CarSharingException, SQLException {
        menuStateHolder.changeState(menuPoint);
    }

    public boolean isExitClicked() {
        return menuStateHolder.getSelectedMenu() instanceof ExitMenu;
    }

    public MenuStateHolder getMenuStateHolder() {
        return menuStateHolder;
    }

    public void setMenuStateHolder(MenuStateHolder menuStateHolder) {
        this.menuStateHolder = menuStateHolder;
    }
}
