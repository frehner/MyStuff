package edu.byu.isys413.afreh20.mystuff;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

//import edu.byu.isys413.afreh20.actions.BatchEmail;

public class EmailReminder extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text txtMessage;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public EmailReminder(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setSize(250, 200);
		shell.setText(getText());
		shell.setLayout(new BorderLayout(0, 0));
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayoutData(BorderLayout.CENTER);
		composite.setLayout(new GridLayout(2, false));
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Label lblRunEmailReminder = new Label(composite, SWT.NONE);
		lblRunEmailReminder.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblRunEmailReminder.setText("Run Email Reminder Process");
		new Label(composite, SWT.NONE);
		
		Button btnRun = new Button(composite, SWT.NONE);
		btnRun.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try{
					LinkedList<Rental> overDue = new LinkedList<Rental>();
					List<BusinessObject> allRentals = BusinessObjectDAO.getInstance().searchForAll("Rental");
					for(BusinessObject bo : allRentals){
						Rental tempr = (Rental) bo;
						Date d1 = new Date();
						
						int diffInDays = (int)( (d1.getTime() - tempr.getDateDue().getTime()) / (1000 * 60 * 60 * 24) );
						System.out.println(diffInDays);
						
						if(tempr.getDateIn()== null && !tempr.isReminderSent() && diffInDays > 0){
							Transaction temptrans = BusinessObjectDAO.getInstance().read(tempr.getTransaction_id());
							Customer tempcust = BusinessObjectDAO.getInstance().read(temptrans.getCustomer_id());
							
							BatchEmail be = new BatchEmail();
							//TODO the next line should be uncommented for live stuff.  I just don't wanna spam people.
//							be.send("noreply@intexmystuff.com", "Do not reply", tempcust.getEmail(), "Validate Account", "Your rental is overdue. Please return it or pay the consequences.");
							
							tempr.setReminderSent(true);
							tempr.save();
							System.out.println("should have sent");
							txtMessage.setText("Emails have been sent.");
						}else{
							txtMessage.setText("No errors, but no emails sent.");
						}
					}
					
				}catch(Exception e2){
					e2.printStackTrace();
					txtMessage.setText("Falied");
				}
			}
		});
		GridData gd_btnRun = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_btnRun.widthHint = 95;
		btnRun.setLayoutData(gd_btnRun);
		btnRun.setText("Run");
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Label lblMessage = new Label(composite, SWT.NONE);
		lblMessage.setText("Message:");
		new Label(composite, SWT.NONE);
		
		txtMessage = new Text(composite, SWT.BORDER);
		txtMessage.setEditable(false);
		txtMessage.setText("message");
		GridData gd_txtMessage = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_txtMessage.widthHint = 153;
		txtMessage.setLayoutData(gd_txtMessage);

	}

}
