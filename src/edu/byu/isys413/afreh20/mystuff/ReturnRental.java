package edu.byu.isys413.afreh20.mystuff;

import java.util.Date;
import java.util.LinkedList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ReturnRental extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text txtProdnum;
	private Text txtMessage;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ReturnRental(Shell parent, int style) {
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
		shell.setSize(275, 200);
		shell.setText(getText());
		shell.setLayout(new BorderLayout(0, 0));
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayoutData(BorderLayout.CENTER);
		composite.setLayout(new GridLayout(3, false));
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Label lblProductNumber = new Label(composite, SWT.NONE);
		lblProductNumber.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblProductNumber.setText("Product Number:");
		
		txtProdnum = new Text(composite, SWT.BORDER);
		txtProdnum.setText("prodnum");
		txtProdnum.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Button btnReturn = new Button(composite, SWT.NONE);
		btnReturn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try{
					Product p1 = BusinessObjectDAO.getInstance().searchForBO("Product", new SearchCriteria("prod_num", Integer.parseInt(txtProdnum.getText())));
					ForRent fr1 = BusinessObjectDAO.getInstance().read(p1.getId());
					LinkedList<BusinessObject> rentalList = (LinkedList<BusinessObject>) BusinessObjectDAO.getInstance().searchForList("Rental", new SearchCriteria("forrentid", fr1.getId()));
					
					for(BusinessObject bo: rentalList){
						Rental tempr = (Rental)bo;
						if(tempr.getDateIn() == null){
							tempr.setDateIn(new Date());
							fr1.setStatus("available");
							tempr.save();
							fr1.save();
							break;
						}
					}
					txtMessage.setText("Returned!");
					
				}catch(Exception e1){
//					e1.printStackTrace();
					txtMessage.setText("Failed, sorry");
				}
			}
		});
		GridData gd_btnReturn = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_btnReturn.widthHint = 94;
		btnReturn.setLayoutData(gd_btnReturn);
		btnReturn.setText("Return");
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Label lblMessage = new Label(composite, SWT.NONE);
		lblMessage.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblMessage.setText("Message:");
		
		txtMessage = new Text(composite, SWT.BORDER);
		txtMessage.setEditable(false);
		txtMessage.setText("message");
		txtMessage.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

	}
}
