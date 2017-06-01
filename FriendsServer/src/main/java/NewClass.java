
import com.luizalabs.friendsserver.dao.FriendDao;
import com.luizalabs.friendsserver.dao.FriendDaoImpl;
import com.luizalabs.friendsserver.model.Friend;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alanfrank
 */
public class NewClass {
    public static void main(String[] args) {
	System.out.println("Eu sou o seu primeiro programa.");
	FriendDao dao = new FriendDaoImpl();
        Friend f = new Friend();
        f.setName("João Silva");
        f.setEmail("alanfrnk@gmail.com");
        f.setCity("Ribeirão Preto");
        f.setBirthDate(new Date());
        
        dao.save(f);
    }
}
