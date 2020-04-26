package cn.sdut.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.sdut.po.Account;


public class AccountDao extends BaseDao{
	
	//add
	public int addAccount(Account account)
	{
		int result=0;
		try {
			con=getConnection();
			String sql="insert into account values(null,?,?)";
			pst=con.prepareStatement(sql);
			pst.setString(1, account.getName());
			pst.setDouble(2, account.getMoney());
			result = pst.executeUpdate();
			closeAll();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	//del
	public int delAccount(int id)
	{
		int result=0;
		try {
			con=getConnection();
			String sql="delete from account where id=?";
			pst=con.prepareStatement(sql);
			pst.setInt(1,id);
			result = pst.executeUpdate();
			closeAll();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	//update
	public int updateAccountMoney(int id,double change)
	{
		int result=0;
		try {
			con=getConnection();
			String sql="update account set money=money+? where id=?";
			pst=con.prepareStatement(sql);
			pst.setDouble(1, change);
			pst.setInt(2, id);
			result = pst.executeUpdate();
			closeAll();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	//turnMoney
	public int turnMoney(int idOut,int idIn,double change)
	{
		int flag=-1;
		int result1=0;
		int result2=0;
		try {
			con=getConnection();
			String sql2="update account set money=money+? where id=?";//增加金额
			String sql1="update account set money=money-? where id=?";//减少金额
			
			pst=con.prepareStatement(sql1);
			pst.setDouble(1, change);
			pst.setInt(2, idOut);
			result1 = pst.executeUpdate();
			pst=con.prepareStatement(sql2);
			pst.setDouble(1, change);
			pst.setInt(2, idIn);
			result2 = pst.executeUpdate();
			if (result1*result2==1)
			{
				
				flag=1;
			}
			closeAll();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	//selectAll
	public List<Account> selectAll()
	{
		List<Account> accountList=new ArrayList<Account>();
		try {
			con=getConnection();
			String sql="select * from account";
			pst=con.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next())
			{
				Account account=new Account();
				account.setId(rs.getInt(1));
				account.setName(rs.getString(2));
				account.setMoney(rs.getDouble(3));
				accountList.add(account);
			}
			closeAll();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accountList;
	}
	

}
