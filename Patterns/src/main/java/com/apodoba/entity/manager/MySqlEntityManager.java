package com.apodoba.entity.manager;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.apodoba.annotation.DBField;
import com.apodoba.annotation.DBPrimaryKey;
import com.apodoba.annotation.DBTable;
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
		StringBuffer deleteSql = new StringBuffer("delete from "+ getClassName(entity) + " where ");
		
		
		String idName = "";
		int idValue = -1;
		Field[] fields = entity.getClass().getDeclaredFields();
		for(Field field: fields){
			if(field.isAnnotationPresent(DBPrimaryKey.class) && field.isAnnotationPresent(DBField.class)){
				field.setAccessible(true);
				idValue = (int) field.get(entity);
				idName = field.getAnnotation(DBField.class).name();
				break;
			}
		}
		
		if(idValue != -1 && !idName.isEmpty()){
			deleteSql.append(idName);
			deleteSql.append("=?");
			PreparedStatement preparedStatement = this.connection.createPreparedStatement(deleteSql.toString());
			preparedStatement.setInt(1, idValue);
		
			preparedStatement.executeUpdate();
			preparedStatement.close();
		}
	}

	@Override
	public <T> void update(T entity) throws Exception{
		StringBuffer updateSql = new StringBuffer("update "+getClassName(entity)+" set ");
		Field[] fields = entity.getClass().getDeclaredFields();
		
		for(int i = 0; i < fields.length; i++){
			if(!fields[i].isAnnotationPresent(DBPrimaryKey.class) && fields[i].isAnnotationPresent(DBField.class)){
				
				String fieldName = fields[i].getAnnotation(DBField.class).name();
				if(i != fields.length-1){
					updateSql.append(fieldName+" = ?, ");
				}else if(i == fields.length-1){
					updateSql.append(fieldName + " = ? ");
				}
			}
		}
		
		String idName = "";
		int idValue = -1;
		for(Field field: fields){
			if(field.isAnnotationPresent(DBPrimaryKey.class) && field.isAnnotationPresent(DBField.class)){
				field.setAccessible(true);
				idValue = (int) field.get(entity);
				idName = field.getAnnotation(DBField.class).name();
				break;
			}
		}
		
		if(idValue != -1 && !idName.isEmpty()){
			updateSql.append("where ");
			updateSql.append(idName);
			updateSql.append("=?");
			PreparedStatement preparedStatement = this.connection.createPreparedStatement(updateSql.toString());
			
			int index = 1;
			for(int i = 0; i < fields.length; i++){
				if(!fields[i].isAnnotationPresent(DBPrimaryKey.class) && fields[i].isAnnotationPresent(DBField.class)){
					fields[i].setAccessible(true);
					preparedStatement.setObject(index, fields[i].get(entity));
					++index;
				}
			}
			
			preparedStatement.setObject(index, idValue);

			preparedStatement.executeUpdate();
			preparedStatement.close();
		}
	}

	@Override
	public <T> void insert(T entity) throws Exception{
		StringBuffer insertSql = new StringBuffer("insert into "+getClassName(entity)+" (");
		Field[] fields = entity.getClass().getDeclaredFields();
		
		for(int i = 0; i < fields.length; i++){
			if(!fields[i].isAnnotationPresent(DBPrimaryKey.class) && fields[i].isAnnotationPresent(DBField.class)){
				String fieldName = fields[i].getAnnotation(DBField.class).name();
				if(i != fields.length-1){
					insertSql.append(fieldName + ", ");
				}else if(i == fields.length-1){
					insertSql.append(fieldName + ") ");
				}
			}
		}
		
		insertSql.append(" VALUES ( ");
		for(int i = 0; i < fields.length; i++){
			if(!fields[i].isAnnotationPresent(DBPrimaryKey.class) && fields[i].isAnnotationPresent(DBField.class)){
				if(i != fields.length-1){
					insertSql.append("?, ");
				}else if(i == fields.length-1){
					insertSql.append(" ?) ");
				}
			}
		}
		
		PreparedStatement preparedStatement = this.connection.createPreparedStatement(insertSql.toString());
		int index = 1;
		for(int i = 0; i < fields.length; i++){
			if(!fields[i].isAnnotationPresent(DBPrimaryKey.class) ){
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
		
		String selectSql = "select * from " + entityClass.getAnnotation(DBTable.class).name();
		Statement statement = this.connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery(selectSql);
		
		while (resultSet.next()) {
			T selectEntity = (T) entityClass.newInstance();
			Field[] fields = selectEntity.getClass().getDeclaredFields();
			
			for(Field field: fields){
				field.setAccessible(true);
				Object value = resultSet.getObject(field.getAnnotation(DBField.class).name());
				field.set(selectEntity, value);
				
			}
			allEntity.add(selectEntity);
        }
		return allEntity;
	}

	private <T> String getClassName(T entity){
		return entity.getClass().getAnnotation(DBTable.class).name();
	}

}