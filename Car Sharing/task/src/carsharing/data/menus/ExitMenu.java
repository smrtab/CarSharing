package carsharing.data.menus;

import carsharing.common.CarSharingException;

public class ExitMenu implements Menu {
    @Override
    public boolean validateSelectedPoint(int point) {
        return false;
    }

    @Override
    public Menu handleClick(int point) throws CarSharingException {
        return null;
    }
}
