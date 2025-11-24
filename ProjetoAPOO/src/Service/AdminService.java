package Service;

import java.util.ArrayList;

import model.Admin;
public class AdminService {
	
	private ArrayList<Admin> admins = new ArrayList<>();
	
	public AdminService () {
		Admin adminPadrao = new Admin("Admin","Admin");
		this.admins.add(adminPadrao);
	}
	
		public boolean loginAdmin(String cpf, String senha) {
			for (Admin a : admins) {
				if(a.getCPF() != null && a.getCPF().equals(cpf)
						&& a.getSenha() != null && a.getSenha().equals(senha)) {
					return true;
				}
			}
			return false;	
	}
}
