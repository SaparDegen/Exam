package peaksoft.service.serviceImpl;

import peaksoft.dao.dao.TableDao;
import peaksoft.dao.daoImpl.TableDaoHibernateImpl;
import peaksoft.service.service.TableService;

public class TableServiceImpl implements TableService {

    private final TableDao tableDao = new TableDaoHibernateImpl();

    @Override
    public void createTable() {
        tableDao.createTable();
    }

    public void dropTable() {
        tableDao.dropTable();
    }
}
