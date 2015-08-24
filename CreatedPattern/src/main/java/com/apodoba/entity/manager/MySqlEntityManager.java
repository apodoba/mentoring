package com.apodoba.entity.manager;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.apodoba.connection.Connection;
import com.apodoba.connection.ConnectionPull;
import com.apodoba.process.ProcessConnection;

public class MySqlEntityManager implements EntityManager {

	private final Connection connection;
	private final Properties properties = new Properties();

	public MySqlEntityManager(String mapping) {
		this.connection = ProcessConnection.getConnection();
		try {
			properties.load(ConnectionPull.class.getResourceAsStream("/"+mapping));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public <T> void delete(T entity) throws Exception {
		String deleteSql = "delete from "+ getClassName(entity) + " where id=?";
		
		Field idField = entity.getClass().getDeclaredField("id");
		idField.setAccessible(true);
		int id = (int) idField.get(entity);
		
		PreparedStatement preparedStatement = this.connection.createPreparedStatement(deleteSql);
		preparedStatement.setInt(1, id);
		
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}

	@Override
	public <T> void update(T entity) throws Exception{
		StringBuffer updateSql = new StringBuffer("update "+getClassName(entity)+" set ");
		Field[] fields = entity.getClass().getDeclaredFields();
		
		for(int i = 0; i < fields.length; i++){
			if(!"id".equals(fields[i].getName()) && i != fields.length-1){
				updateSql.append(fields[i].getName()+" = ?, ");
			}else if(i == fields.length-1){
				updateSql.append(fields[i].getName() + " = ? ");
			}
		}
		
		updateSql.append("where id=?");
		
		PreparedStatement preparedStatement = this.connection.createPreparedStatement(updateSql.toString());
		int index = 1;
		for(int i = 0; i < fields.length; i++){
			fields[i].setAccessible(true);
			if(!"id".equals(fields[i].getName())){
				preparedStatement.setObject(index, fields[i].get(entity));
				++index;
			}
		}
		
		Field idField = entity.getClass().getDeclaredField("id");
		idField.setAccessible(true);
		int id = (int) idField.get(entity);
		preparedStatement.setObject(index, id);
		
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}

	@Override
	public <T> void insert(T entity) throws Exception{
		StringBuffer insertSql = new StringBuffer("insert into "+getClassName(entity)+" (");
		Field[] fields = entity.getClass().getDeclaredFields();
		
		for(int i = 0; i < fields.length; i++){
			if(!"id".equals(fields[i].getName()) && i != fields.length-1){
				insertSql.append(fields[i].getName() + ", ");
			}else if(i == fields.length-1){
				insertSql.append(fields[i].getName() + ") ");
			}
		}
		
		insertSql.append(" VALUES ( ");
		for(int i = 0; i < fields.length; i++){
			if(!"id".equals(fields[i].getName()) && i != fields.length-1){
				insertSql.append("?, ");
			}else if(i == fields.length-1){
				insertSql.append(" ?) ");
			}
		}
		
		PreparedStatement preparedStatement = this.connection.createPreparedStatement(insertSql.toString());
		int index = 1;
		for(int i = 0; i < fields.length; i++){
			if(!"id".equals(fields[i].getName())){
				fields[i].setAccessible(true);
				preparedStatement.setObject(index, fields[i].get(entity));
				++index;
			}
		}
		preparedStatement.executeUpdate();
		preparedStatement.close();
		
	}

	@Override
	public <T> List<T> selectAll(Class<T> entityClass) throws Exception{
		List<T> allEntity = new ArrayList<T>();
		
		String selectSql = "select * from " + entityClass.getSimpleName();
		Statement statement = this.connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery(selectSql);
		
		while (resultSet.next()) {
			T selectEntity = (T) entityClass.newInstance();
			Field[] fields = selectEntity.getClass().getDeclaredFields();
			
			for(Field field: fields){
				field.setAccessible(true);
				Object value = resultSet.getObject(field.getName());
				field.set(selectEntity, value);
				
			}
			allEntity.add(selectEntity);
        }
		return allEntity;
	}

	private <T> String getClassName(T entity){
		return entity.getClass().getSimpleName();
	}

}