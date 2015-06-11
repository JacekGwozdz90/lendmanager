package lendmanager.notifications;

import java.util.Properties;
import java.util.TimerTask;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import lendmanager.account.Account;
import lendmanager.account.AccountRepository;
import lendmanager.items.Item;


public class EmailSendTask extends TimerTask {


	private AccountRepository accountRepository;
	private Item item;

	public EmailSendTask(AccountRepository accountRepository,Item item) {
		this.item = item;
		this.accountRepository = accountRepository;

	}

	@Override
	public void run() {

		String ownerMail = null;
		String lendMail = null;
		System.out.println("Sending new emails!");
		for (Account account : accountRepository.findAll()) {
			if (account.getId().equals(item.getOwner().getAccountId())) {
				ownerMail = account.getEmail();
			}
			if (account.getId().equals(item.getPerson().getFacebookId())) {
				lendMail = account.getEmail();
			}

		}
		System.out.println(ownerMail);
		System.out.println(lendMail);
		
		String subject = "Reminder about item:" + item.getName();
		String lendMailInfo = "";
		if (lendMail != null) {
			lendMailInfo = "The contact mail to "
					+ item.getPerson().getFirstName() + " is " + lendMail;
		}

		String body = "Hello!\nYou have lent item: " + item.getName()
				+ "to the " + item.getPerson().getFirstName()
				+ item.getPerson().getLastName() + " on the "
				+ item.getLendDate() + "\n Return date is set to : "
				+ item.getReturnDate() + lendMailInfo
				+ "\n\n Have a nice day!\n" + item.getOwner().getFirstName()
				+ item.getOwner().getLastName();
		sendMail(ownerMail, subject, body);
		
		if(lendMail!=null){
			body = "Hello!\nYou have borrow item: " + item.getName()
					+ "from the " + item.getOwner().getFirstName()
					+ item.getOwner().getLastName() + " on the "
					+ item.getLendDate() + "\n Return date is set to : "
					+ item.getReturnDate() 
					+ "\n\n Have a nice day!\n" + item.getPerson().getFirstName()
					+ item.getOwner().getLastName();
			sendMail(lendMail, subject, body);
		}
	}

	private void sendMail(String mailAddress, String subject, String body) {
		String[] to = {mailAddress};
		Properties props = System.getProperties();
		String from = "lendmanager@gmail.com";
		String pass = "sius2015";
		String host = "smtp.gmail.com";
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(from));
			InternetAddress[] toAddress = new InternetAddress[to.length];

			// To get the array of addresses
			for (int i = 0; i < to.length; i++) {
				toAddress[i] = new InternetAddress(to[i]);
			}

			for (int i = 0; i < toAddress.length; i++) {
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}

			message.setSubject(subject);
			message.setText(body);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (AddressException ae) {
			ae.printStackTrace();
		} catch (MessagingException me) {
			me.printStackTrace();
		}
	}

}
