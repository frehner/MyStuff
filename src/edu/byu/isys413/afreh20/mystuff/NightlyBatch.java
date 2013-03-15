package edu.byu.isys413.afreh20.mystuff;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;

public class NightlyBatch extends Dialog {

	protected Object result;
	protected Shell shlNightlyBatchWindow;
	private Text txtMessage;
	private List<BusinessObject> allDCs = new LinkedList<BusinessObject>();
	private List<BusinessObject> allJEs = new LinkedList<BusinessObject>();
	private SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
	private Button btnCancel;
	private DateTime txtDate;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public NightlyBatch(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlNightlyBatchWindow.open();
		shlNightlyBatchWindow.layout();
		Display display = getParent().getDisplay();
		while (!shlNightlyBatchWindow.isDisposed()) {
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
		shlNightlyBatchWindow = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shlNightlyBatchWindow.setSize(250, 145);
		shlNightlyBatchWindow.setText("Nightly Batch Window");
		shlNightlyBatchWindow.setLayout(new GridLayout(3, false));
		new Label(shlNightlyBatchWindow, SWT.NONE);
		
		Label lblMessage = new Label(shlNightlyBatchWindow, SWT.NONE);
		lblMessage.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblMessage.setText("Message:");
		
		txtMessage = new Text(shlNightlyBatchWindow, SWT.BORDER);
		txtMessage.setEnabled(false);
		txtMessage.setEditable(false);
		txtMessage.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(shlNightlyBatchWindow, SWT.NONE);
		
		Label lblSelectDate = new Label(shlNightlyBatchWindow, SWT.NONE);
		lblSelectDate.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSelectDate.setText("Select Date:");
		
		txtDate = new DateTime(shlNightlyBatchWindow, SWT.BORDER);
		GridData gd_txtDate = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txtDate.widthHint = 104;
		txtDate.setLayoutData(gd_txtDate);
		new Label(shlNightlyBatchWindow, SWT.NONE);
		
		btnCancel = new Button(shlNightlyBatchWindow, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlNightlyBatchWindow.dispose();
			}
		});
		GridData gd_btnCancel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnCancel.widthHint = 58;
		btnCancel.setLayoutData(gd_btnCancel);
		btnCancel.setText("Cancel");
		
		Button btnUpdateLedgers = new Button(shlNightlyBatchWindow, SWT.NONE);
		btnUpdateLedgers.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
//					Date date1 = SDF.parse(txtDate.getText());
//					System.out.println(date1);
					//getting journal entries
//					allJEs = BusinessObjectDAO.getInstance().searchForList("JournalEntry", new SearchCriteria("date", date1, SearchCriteria.GREATER_THAN_OR_EQUALS), new SearchCriteria("date", date2, SearchCriteria.LESS_THAN));
//					allJEs = BusinessObjectDAO.getInstance().searchForList("JournalEntry", new SearchCriteria("date", date1));
//					System.out.println("After allJEs should be filled");
//					System.out.println(allJEs.size());
//					for(BusinessObject bo1 : allJEs){
//						//getting all debitcredit objects and adding to list
//						JournalEntry je1 = (JournalEntry) bo1;
//						System.out.println(je1.getDate() + "lkasjdflkjasdflkasjdf");
//						List<BusinessObject> tempList = (BusinessObjectDAO.getInstance().searchForList("JournalEntry", new SearchCriteria("journalentry_id", je1.getId()), new SearchCriteria("batchrun", 0)));
//						for(BusinessObject bo2: tempList){
//							allDCs.add(bo2);
//						}
//					}
//					
					allDCs = BusinessObjectDAO.getInstance().searchForList("DebitCredit", new SearchCriteria("batchrun", false));
					for(BusinessObject bo3:allDCs){
						DebitCredit dc1 = (DebitCredit) bo3;
						//get the general ledger
						GenLedger gl1 = BusinessObjectDAO.getInstance().searchForBO("GenLedger", new SearchCriteria("name", dc1.getGenledger_name()));
						//if debit, add; if credit, subtract
						if(dc1.isIsdebit()){
							gl1.addDebit(dc1.getAmount());
						}else{
							gl1.addCredit(dc1.getAmount());
						}
						dc1.setBatchRun(true);
						dc1.save();
						gl1.save();
						
					}
					txtMessage.setText("Done. Rejoice");
					
				} catch (Exception e1) {
					e1.printStackTrace();
					txtMessage.setText("Failed");
				}
			}
		});
		GridData gd_btnUpdateLedgers = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnUpdateLedgers.widthHint = 125;
		btnUpdateLedgers.setLayoutData(gd_btnUpdateLedgers);
		btnUpdateLedgers.setText("Update Ledgers");

	}

}
