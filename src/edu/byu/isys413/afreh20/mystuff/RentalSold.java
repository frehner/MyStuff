package edu.byu.isys413.afreh20.mystuff;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class RentalSold extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text txtMessage;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public RentalSold(Shell parent, int style) {
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
		shell.setSize(300, 220);
		shell.setText(getText());
		shell.setLayout(new BorderLayout(0, 0));
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayoutData(BorderLayout.CENTER);
		composite.setLayout(new GridLayout(1, false));
		
		Label lblTosellProducts = new Label(composite, SWT.NONE);
		lblTosellProducts.setText("To \"sell\" products that are 10 days or more overdue");
		
		Button btnsellProducts = new Button(composite, SWT.NONE);
		btnsellProducts.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try{
					
					List<BusinessObject> allRentals = BusinessObjectDAO.getInstance().searchForAll("Rental");
					for(BusinessObject bo : allRentals){
						Rental tempr = (Rental) bo;
						
						Date d1 = new Date();
						
						int diffInDays = (int)( (d1.getTime() - tempr.getDateDue().getTime()) / (1000 * 60 * 60 * 24) );
						System.out.println(diffInDays);
						
						if(tempr.getDateIn()== null && diffInDays > 10){
							ForRent fr1 = BusinessObjectDAO.getInstance().read(tempr.getForrentid());
							
							Transaction oldtrans = BusinessObjectDAO.getInstance().read(tempr.getTransaction_id());
							Customer cust = BusinessObjectDAO.getInstance().read(oldtrans.getCustomer_id());
							
							fr1.setStatus("Sold");
							fr1.save();
							tempr.setDateIn(new Date());
							tempr.save();
							
							Transaction trans = BusinessObjectDAO.getInstance().create("Transaction");
							Sale sale = BusinessObjectDAO.getInstance().create("Sale");
							Store store = BusinessObjectDAO.getInstance().read(fr1.getStore_id());
							
							sale.setProduct_id(fr1.getId());
							sale.setQuantity(1);
							sale.setTransaction_id(trans.getId());
							sale.setType("Sale");
							ConceptualRental crent = BusinessObjectDAO.getInstance().read(fr1.getCprod_id());
							sale.setChargeamt(crent.getReplacePrice());
							sale.save();
							
							Payment pay = BusinessObjectDAO.getInstance().create("Payment");
							pay.setAmount(crent.getReplacePrice());
							pay.setPay_change(0);
							pay.setTransaction_id(trans.getId());
							pay.setType("Card");
							pay.save();
							
							trans.setDate(new Date());
							trans.setEmployee_id(store.getManagerid());
							trans.setStore_id(store.getId());
							trans.setSubtotal(crent.getReplacePrice());
							trans.setTax(crent.getReplacePrice() * store.getSalestaxrate());
							trans.setTotal((crent.getReplacePrice() * store.getSalestaxrate()) + crent.getReplacePrice());
							ArrayList<RevenueSource> temptemp = new ArrayList<RevenueSource>();
							temptemp.add(sale);
							trans.setRevSource(temptemp);
							trans.setCommissionTotal(0.0);
							trans.setCustomer_id(cust.getId());							
							trans.save();
							
							JournalEntry je = BusinessObjectDAO.getInstance().create("JournalEntry");
							je.setTransaction_id(trans.getId());
							je.setDate(new Date());
							je.save();
							
							for(int i=0;i<6;i++){
								DebitCredit dc = BusinessObjectDAO.getInstance().create("DebitCredit");
								if(i==0){
									dc.setAmount(trans.getTotal());
									dc.setGenledger_name("Cash");
									dc.setIsdebit(true);
									dc.setJournalentry_id(je.getId());
									dc.save();
								}
								else if(i==1){
									double salestotal = 0;
									double renttotal = 0;
									
									for(RevenueSource rs3:trans.getRevSource()){
										if(rs3.getType().equals("Sale")){
											salestotal += rs3.getChargeamt();
										}else{
											renttotal += rs3.getChargeamt();
										}
									}
									if(salestotal > 0){
										dc.setAmount(salestotal);
										dc.setGenledger_name("Sales Revenue");
										dc.setIsdebit(false);
										dc.setJournalentry_id(je.getId());
										dc.save();
									}
									if(renttotal > 0){
										DebitCredit dc1 = BusinessObjectDAO.getInstance().create("DebitCredit");
										dc1.setAmount(salestotal);
										dc1.setGenledger_name("Rental Revenue");
										dc1.setIsdebit(false);
										dc1.setJournalentry_id(je.getId());
										dc1.save();
									}
									
								}
								else if(i==2){
									dc.setAmount(trans.getTax());
									dc.setGenledger_name("Sales Tax");
									dc.setIsdebit(false);
									dc.setJournalentry_id(je.getId());
									dc.save();
								}
								else if(i==3){
									dc.setAmount(trans.getCommissionTotal());
									dc.setGenledger_name("Commission Expense");
									dc.setIsdebit(true);
									dc.setJournalentry_id(je.getId());
									dc.save();
								}
								else if(i==4){
									dc.setAmount(trans.getCommissionTotal());
									dc.setGenledger_name("Commission Payable");
									dc.setIsdebit(false);
									dc.setJournalentry_id(je.getId());
									dc.save();
								}
								else if (i == 5) {
									dc.setAmount(trans.getSubtotal());
									dc.setGenledger_name("Inventory");
									dc.setIsdebit(false);
									dc.setJournalentry_id(je.getId());
									dc.save();
								}
							}
							
							BatchEmail be = new BatchEmail();
							//TODO the next line should be uncommented for live stuff.  I just don't wanna spam people.
							be.send("noreply@intexmystuff.com", "Do not reply", cust.getEmail(), "Validate Account", "Congrats, you just bought that rental item that is overdue.  Yay!");
							txtMessage.setText("Rentals sold!");
						}else{
							txtMessage.setText("No sales because no overdue rentals");
						}
						
					}
				}catch(Exception e2){
					e2.printStackTrace();
					txtMessage.setText("Error, something failed");
				}
				
				
				
			}
		});
		btnsellProducts.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		btnsellProducts.setText("\"Sell\" Products");
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Label lblMessage = new Label(composite, SWT.NONE);
		lblMessage.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblMessage.setText("Message:");
		
		txtMessage = new Text(composite, SWT.BORDER);
		txtMessage.setEditable(false);
		txtMessage.setText("Wait for confirmation here");
		txtMessage.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

	}

}
