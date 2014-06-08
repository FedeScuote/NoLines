package tests;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.LinkedList;

import org.junit.Test;


import dao.exception.DaoException;
import entity.voucher;
import DaoImpl.VoucherDaoDB;

public class TestVoucherDaoDB {
	VoucherDaoDB voucherDao = new VoucherDaoDB();

	@Test
	public void testCreateVoucherForUser() {
		try {
			boolean resultado=false;
			Date tiempo = new Date();
			//Crea un voucher para el usuario en el restaurante con id 2.
			voucherDao.createVoucherForUser("jt.tejeria@gmail.com",2);
			
			java.sql.Date sqlDate = new java.sql.Date(tiempo.getTime());
			
			//Busquemos en base de datos si existe un voucher nuevo para ese usuario en ese restaurante en esta hora.
			LinkedList<voucher> vouchers = voucherDao.getVouchers("jt.tejeria@gmail.com");
			for (voucher vo : vouchers) {
				if(vo.getShopId() == 2){
					resultado=true;
				}
			}
			
		} catch (DaoException e) {
			fail("No corrio el metodo createVoucherForUser");
		}
	}

	@Test
	public void testCreateVoucher(){
		try {
			Date tiempo = new Date();
			java.sql.Date sqlDate = new java.sql.Date(tiempo.getTime());
			
			voucherDao.createVoucher(3);
			
			LinkedList<voucher> vouchers = voucherDao.getVouchers("jt.tejeria@gmail.com");
			LinkedList<voucher> vouchersPepe = voucherDao.getVouchers("pepe@gmail.com");
			
			boolean ingreso1=false;
			boolean ingreso2=false;
			for (voucher vo : vouchers) {
				if(vo.getShopId() == 3){	
					ingreso1=true;
				}
			}
			for (voucher vou : vouchersPepe) {
				if(vou.getShopId() == 3){
					ingreso2=true;
				}
			}

			boolean ingresoCorrecto = (ingreso1 && ingreso2);
			assertTrue(ingresoCorrecto);
		} catch (DaoException e) {
			fail();
		}
		
	}
	
	@Test
	public void testValidateVoucher(){
		try {
			boolean valido = voucherDao.validateVoucher("jt.tejeria@gmail.com",1);
			boolean noValido = voucherDao.validateVoucher("jt.tejeria@gmai.com",2);
			assertTrue(valido);
			assertFalse(noValido);
		} catch (DaoException e) {
			fail();
		}
	}
}
