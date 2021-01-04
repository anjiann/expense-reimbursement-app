package repositories;

import util.ConnectionFactory;

public abstract class DAO {
	protected ConnectionFactory cf = ConnectionFactory.getConnectionFactory();

}
