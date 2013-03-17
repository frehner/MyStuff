package edu.byu.isys413.afreh20.mystuff;

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
