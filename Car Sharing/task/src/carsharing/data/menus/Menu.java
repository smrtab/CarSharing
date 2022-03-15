package carsharing.data.menus;

import carsharing.common.CarSharingException;

import java.sql.SQLException;

public interface Menu {
    boolean validateSelectedPoint(int point);
    Menu handleClick(int point) throws CarSharingException, SQLException;
}
