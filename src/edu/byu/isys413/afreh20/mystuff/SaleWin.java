package edu.byu.isys413.afreh20.mystuff;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import swing2swt.layout.BorderLayout;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;

public class SaleWin {
	@SuppressWarnings("unused")
	private static class ContentProvider implements IStructuredContentProvider {
		public Object[] getElements(Object inputElement) {
			return new Object[0];
		}

		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}

	protected Shell shlSaleWindow;
	private Text textStore;
	private Text textEmployee;
	private Table table;
	private TableViewer prodTable;
	private Text txtCustName;
	private Text txtPhone;
	private Text txtAddress;
	private Text textCustSearch;
	private Text txtProdSearch;
	private Text txtProdName;
	private Text txtPrice;
	private Text txtSubtotal;
	private Text txtTax;
	private Text txtTotal;
	private TableViewerColumn tcProduct;
	private TableViewerColumn tcQuantity;
	private TableViewerColumn tcPrice;
	private Spinner quantity;
	private Button btnAddProduct;
	private Employee emp;
	private Store store;
	private LoginWin loginwin;
	private Customer cust = null;
	private Transaction trans;
	private Product prod;
	@SuppressWarnings("unused")
	private SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
	private Label lblQuantity;
//	private RevenueSource revSource;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SaleWin window = new SaleWin();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Presents the login modal window. If correctly authenticated, then we get
	 * the employee from that window and set the information.
	 */
	private void doLoginWin() {
		loginwin = new LoginWin(shlSaleWindow, 0);
		loginwin.open();
		try {
			if (loginwin.isSuccessful()) {
				emp = loginwin.getEmp();
				textEmployee.setText(emp.getFirstname() + " " + emp.getLastname());
				if (emp.getStoreId() != null) {
					store = BusinessObjectDAO.getInstance().read(emp.getStoreId());
					textStore.setText(store.getAddress());
				} else {
					textStore.setText("No store assigned.");
				}
			} else {
				shlSaleWindow.dispose();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		try {
			CreateDB.main(null);
			trans = BusinessObjectDAO.getInstance().create("Transaction");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Display display = Display.getDefault();
		createContents();
		shlSaleWindow.open();
		shlSaleWindow.layout();
		
		//////////////////////////////////////////////////////////////////TODO change this.
		//change the following lines between commented and uncommented to 
		//skip the login window (and also set an emp, store, and cust auto)
		
//		 doLoginWin();
		
		try {
			this.emp = BusinessObjectDAO.getInstance().read("employee1");
			this.store = BusinessObjectDAO.getInstance().read("store1");
			this.cust = BusinessObjectDAO.getInstance().read("customer1");
		} catch (DataException e) {
			e.printStackTrace();
		}
		
		//end switching of comments
		////////////////////////////
		
		while (!shlSaleWindow.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlSaleWindow = new Shell();
		shlSaleWindow.setSize(600, 450);
		shlSaleWindow.setText("Sale Window");
		shlSaleWindow.setLayout(new BorderLayout(0, 0));

		Menu menu = new Menu(shlSaleWindow, SWT.BAR);
		shlSaleWindow.setMenuBar(menu);

		MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText("File");

		Menu menu_1 = new Menu(mntmFile);
		mntmFile.setMenu(menu_1);

		MenuItem mntmEdit = new MenuItem(menu, SWT.CASCADE);
		mntmEdit.setText("Edit");

		Menu menu_2 = new Menu(mntmEdit);
		mntmEdit.setMenu(menu_2);

		MenuItem mntmView = new MenuItem(menu, SWT.CASCADE);
		mntmView.setText("View");

		Menu menu_3 = new Menu(mntmView);
		mntmView.setMenu(menu_3);

		MenuItem mntmBatch = new MenuItem(menu, SWT.CASCADE);
		mntmBatch.setText("Batch");

		Menu menu_4 = new Menu(mntmBatch);
		mntmBatch.setMenu(menu_4);
		
		MenuItem mntmDailyBatchWindow = new MenuItem(menu_4, SWT.NONE);
		mntmDailyBatchWindow.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				NightlyBatch nbwin = new NightlyBatch(shlSaleWindow, 0);
				nbwin.open();
			}
		});
		mntmDailyBatchWindow.setText("Daily Batch Window");

		MenuItem mntmMaintenance = new MenuItem(menu, SWT.CASCADE);
		mntmMaintenance.setText("Maintenance");

		Menu menu_5 = new Menu(mntmMaintenance);
		mntmMaintenance.setMenu(menu_5);

		MenuItem mntmHelp = new MenuItem(menu, SWT.CASCADE);
		mntmHelp.setText("Help");

		Menu menu_6 = new Menu(mntmHelp);
		mntmHelp.setMenu(menu_6);

		Composite composite = new Composite(shlSaleWindow, SWT.BORDER);
		composite.setLayoutData(BorderLayout.NORTH);
		composite.setLayout(new GridLayout(5, false));

		Label lblStore = new Label(composite, SWT.NONE);
		lblStore.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblStore.setText("Store:");

		textStore = new Text(composite, SWT.BORDER);
		textStore.setEnabled(false);
		textStore.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("Employee");

		textEmployee = new Text(composite, SWT.BORDER);
		textEmployee.setEnabled(false);
		textEmployee.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button btnLogout = new Button(composite, SWT.NONE);
		btnLogout.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				emp = null;
				loginwin.setEmp(null);
				loginwin.setSuccessful(false);
				textEmployee.setText("");
				textStore.setText("");
				doLoginWin();
			}
		});
		btnLogout.setText("Logout");

		Composite composite_7 = new Composite(shlSaleWindow, SWT.NONE);
		composite_7.setLayoutData(BorderLayout.CENTER);
		composite_7.setLayout(new RowLayout(SWT.HORIZONTAL));

		Composite composite_1 = new Composite(composite_7, SWT.BORDER);
		composite_1.setLayoutData(new RowData(160, SWT.DEFAULT));
		composite_1.setLayout(new BorderLayout(0, 0));

		Label lblCustomer = new Label(composite_1, SWT.CENTER);
		lblCustomer.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblCustomer.setLayoutData(BorderLayout.NORTH);
		lblCustomer.setText("Customer");

		Composite composite_4 = new Composite(composite_1, SWT.NONE);
		composite_4.setLayoutData(BorderLayout.SOUTH);
		composite_4.setLayout(new GridLayout(1, false));

		Label label_1 = new Label(composite_4, SWT.NONE);
		label_1.setText("Search:");

		textCustSearch = new Text(composite_4, SWT.BORDER | SWT.SEARCH);
		textCustSearch.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textCustSearch.getText().equals("Search")) {
					textCustSearch.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textCustSearch.getText().equals("")) {
					textCustSearch.setText("Search");
				}
			}
		});
		textCustSearch.setText("Search");
		textCustSearch.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button btnSearchByPhone = new Button(composite_4, SWT.NONE);
		btnSearchByPhone.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					cust = BusinessObjectDAO.getInstance().searchForBO("Customer", new SearchCriteria("phone", textCustSearch.getText()));
					txtCustName.setText(cust.getFirstname());
					txtPhone.setText(cust.getPhone());
					txtAddress.setText(cust.getAddress());
				} catch (Exception ee) {
					// ee.printStackTrace();
					txtCustName.setText("Not found");
					txtPhone.setText("");
					txtAddress.setText("");
				}
			}
		});
		GridData gd_btnSearchByPhone = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnSearchByPhone.widthHint = 149;
		btnSearchByPhone.setLayoutData(gd_btnSearchByPhone);
		btnSearchByPhone.setText("Search by Phone");

		Label label_2 = new Label(composite_4, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		Label lblName = new Label(composite_4, SWT.NONE);
		lblName.setText("Name:");

		txtCustName = new Text(composite_4, SWT.BORDER);
		txtCustName.setEditable(false);
		txtCustName.setText("Name");
		txtCustName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblPhone = new Label(composite_4, SWT.NONE);
		lblPhone.setText("Phone:");

		txtPhone = new Text(composite_4, SWT.BORDER);
		txtPhone.setEditable(false);
		txtPhone.setText("Phone");
		txtPhone.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblAddress = new Label(composite_4, SWT.NONE);
		lblAddress.setText("Address:");

		txtAddress = new Text(composite_4, SWT.BORDER);
		txtAddress.setEditable(false);
		txtAddress.setText("Address");
		txtAddress.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label = new Label(composite_4, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		Button btnNewCustomer = new Button(composite_4, SWT.NONE);
		btnNewCustomer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cust = null;
				customerInfoWin();
			}
		});
		btnNewCustomer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		btnNewCustomer.setText("New Customer");

		Button btnEditCustomer = new Button(composite_4, SWT.NONE);
		btnEditCustomer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				customerInfoWin();
			}
		});
		btnEditCustomer.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnEditCustomer.setText("Edit Customer");

		Button btnAnonymous = new Button(composite_4, SWT.NONE);
		btnAnonymous.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					cust = BusinessObjectDAO.getInstance().searchForBO("Customer", new SearchCriteria("id", "anon"));
					txtCustName.setText(cust.getFirstname());
					txtPhone.setText("");
					txtAddress.setText("");
				} catch (Exception ee) {
					// ee.printStackTrace();
					txtCustName.setText("Not found");
					txtPhone.setText("");
					txtAddress.setText("");
				}
			}
		});
		btnAnonymous.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnAnonymous.setText("Anonymous Customer");

		Composite composite_2 = new Composite(composite_7, SWT.BORDER);
		composite_2.setLayoutData(new RowData(148, 344));
		composite_2.setLayout(new BorderLayout(0, 0));

		Label lblNewLabel_1 = new Label(composite_2, SWT.CENTER);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblNewLabel_1.setLayoutData(BorderLayout.NORTH);
		lblNewLabel_1.setText("Product");

		Composite composite_5 = new Composite(composite_2, SWT.NONE);
		composite_5.setLayoutData(BorderLayout.CENTER);
		composite_5.setLayout(new GridLayout(1, false));

		Label lblProdSearch = new Label(composite_5, SWT.NONE);
		lblProdSearch.setText("Search:");

		txtProdSearch = new Text(composite_5, SWT.BORDER | SWT.SEARCH);
		txtProdSearch.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtProdSearch.getText().equals("Search")) {
					txtProdSearch.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtProdSearch.getText().equals("")) {
					txtProdSearch.setText("Search");
				}
			}
		});
		txtProdSearch.setText("Search");
		txtProdSearch.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button btnSearchByNumber = new Button(composite_5, SWT.NONE);
		btnSearchByNumber.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (txtProdSearch.getText() != null) {
					try {
						Product p = BusinessObjectDAO.getInstance().searchForBO("Product", new SearchCriteria("prod_num", Integer.parseInt(txtProdSearch.getText())));
						if (p.getType().equals("PhysicalProd")) {
							PhysicalProd temppprod = BusinessObjectDAO.getInstance().searchForBO("PhysicalProd", new SearchCriteria("pprod_num", p.getProd_num()));
							if(temppprod.getPhystype().equals("ForRent")){
								prod = BusinessObjectDAO.getInstance().searchForBO("ForRent", new SearchCriteria("id", temppprod.getId()));
								lblQuantity.setText("Number of Days");
								btnAddProduct.setText("Add Rental");
							}else{
								prod = BusinessObjectDAO.getInstance().searchForBO("ForSale", new SearchCriteria("id", temppprod.getId()));
								lblQuantity.setText("Quantity");
								btnAddProduct.setText("Add Product");
							}
						} else {
							prod = BusinessObjectDAO.getInstance().searchForBO("ConceptualProd", new SearchCriteria("cprod_num", p.getProd_num()));
							lblQuantity.setText("Quantity");
							btnAddProduct.setText("Add Product");
						}
						txtProdName.setText(prod.getName());
						txtPrice.setText(p.getPrice() + "");

					} catch (Exception e1) {
						// e1.printStackTrace();
						txtProdName.setText("No prod found");
					}

				} else {
					txtProdName.setText("No prod #");
				}
			}
		});
		btnSearchByNumber.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnSearchByNumber.setText("Search by Number");

		Label label_4 = new Label(composite_5, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		Label lblName_1 = new Label(composite_5, SWT.NONE);
		lblName_1.setText("Name:");

		txtProdName = new Text(composite_5, SWT.BORDER);
		txtProdName.setEditable(false);
		txtProdName.setText("Name");
		txtProdName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		this.lblQuantity = new Label(composite_5, SWT.NONE);
		GridData gd_lblQuantity = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_lblQuantity.widthHint = 136;
		lblQuantity.setLayoutData(gd_lblQuantity);
		lblQuantity.setText("Quantity:");

		quantity = new Spinner(composite_5, SWT.BORDER);
		quantity.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				if(Integer.parseInt(quantity.getText()) > 0){
					btnAddProduct.setEnabled(true);
				} else {
					btnAddProduct.setEnabled(false);
				}
			}
		});
		quantity.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		Label lblPrice = new Label(composite_5, SWT.NONE);
		lblPrice.setText("Price:");

		txtPrice = new Text(composite_5, SWT.BORDER);
		txtPrice.setEditable(false);
		txtPrice.setText("Price");
		txtPrice.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_5 = new Label(composite_5, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		this.btnAddProduct = new Button(composite_5, SWT.NONE);
		btnAddProduct.setEnabled(false);
		btnAddProduct.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(prod != null){
					try {
						if(prod.getType().equals("Physical")){
							PhysicalProd temppprod = BusinessObjectDAO.getInstance().read(prod.getId());
							if(temppprod.getPhystype().equals("ForRent")){
								Rental rental = BusinessObjectDAO.getInstance().create("Rental");
								rental.setChargeamt(Integer.parseInt(quantity.getText())*prod.getPrice());
								
								//just to get the stupid due date. I hate dates.
								Date today = new Date();
								Calendar cal = Calendar.getInstance();
								cal.setTime(today);
								cal.add(Calendar.DATE, Integer.parseInt(quantity.getText()));
								rental.setDateDue(cal.getTime());
								
								rental.setDateOut(today);
								rental.setForrentid(temppprod.getId());
//								rental.set
										
							} else{
								Sale sale = BusinessObjectDAO.getInstance().create("Sale");
								sale.setProduct_id(prod.getId());
								sale.setQuantity(Integer.parseInt(quantity.getText()));
								sale.setChargeamt(Integer.parseInt(quantity.getText())*prod.getPrice());
								sale.setTransaction_id(trans.getId());
								sale.setType("Sale");
								sale.save();
								
								trans.setCommissionTotal(sale.getChargeamt() * prod.getCommissionRate());
								
								trans.addRevSource(sale);
								refreshProdTable();
							}
						}else{
							Sale sale = BusinessObjectDAO.getInstance().create("Sale");
							sale.setProduct_id(prod.getId());
							sale.setQuantity(Integer.parseInt(quantity.getText()));
							sale.setChargeamt(Integer.parseInt(quantity.getText())*prod.getPrice());
							sale.setTransaction_id(trans.getId());
							sale.setType("Sale");
							sale.save();
							
							trans.setCommissionTotal(sale.getChargeamt() * prod.getCommissionRate());
							
							trans.addRevSource(sale);
							refreshProdTable();
						}
					} catch (DataException e1) {
//						e1.printStackTrace();
						txtProdName.setText("Failed to add");
					}
				} else {
					txtProdName.setText("No prod found");
				}
				
				
			}
		});
		btnAddProduct.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnAddProduct.setText("Add Product");

		Composite composite_3 = new Composite(composite_7, SWT.BORDER);
		composite_3.setLayoutData(new RowData(252, 346));
		composite_3.setLayout(new BorderLayout(0, 0));

		this.prodTable = new TableViewer(composite_3, SWT.BORDER | SWT.FULL_SELECTION);
		table = prodTable.getTable();
		table.setHeaderVisible(true);
		table.setLayoutData(BorderLayout.CENTER);

		this.tcProduct = new TableViewerColumn(prodTable, SWT.NONE);
		TableColumn tblclmnProduct = tcProduct.getColumn();
		tblclmnProduct.setResizable(false);
		tblclmnProduct.setWidth(115);
		tblclmnProduct.setText("Product");
//		tableViewer.setContentProvider(new ContentProvider());

		this.tcQuantity = new TableViewerColumn(prodTable, SWT.NONE);
		TableColumn tblclmnQuantity = tcQuantity.getColumn();
		tblclmnQuantity.setResizable(false);
		tblclmnQuantity.setWidth(55);
		tblclmnQuantity.setText("Quant");

		this.tcPrice = new TableViewerColumn(prodTable, SWT.NONE);
		TableColumn tblclmnPrice = tcPrice.getColumn();
		tblclmnPrice.setResizable(false);
		tblclmnPrice.setWidth(74);
		tblclmnPrice.setText("Price");
		
		tcProduct.setLabelProvider(new ColumnLabelProvider(){
			public String getText(Object element){
				Sale sale1 = (Sale) element;
				Product prod1;
				try {
					prod1 = BusinessObjectDAO.getInstance().read(sale1.getProduct_id());
					return prod1.getName();
				} catch (DataException e) {
					e.printStackTrace();
				}
				return null;
			}	
		});

		tcQuantity.setLabelProvider(new ColumnLabelProvider(){
			public String getText(Object element){
				Sale sale1 = (Sale) element;
				DecimalFormat df = new DecimalFormat("###");
				return df.format(sale1.getQuantity())+"";
			}	
		});
		
		tcPrice.setLabelProvider(new ColumnLabelProvider(){
			public String getText(Object element){
				Sale sale1 = (Sale) element;
				DecimalFormat df = new DecimalFormat("###.##");
				df.setMinimumFractionDigits(2);
				return df.format(sale1.getChargeamt())+"";
			}
		});
		
		prodTable.setContentProvider(new ArrayContentProvider());
		
		Composite composite_6 = new Composite(composite_3, SWT.NONE);
		composite_6.setLayoutData(BorderLayout.SOUTH);
		composite_6.setLayout(new GridLayout(2, false));

		Label lblSubtotal = new Label(composite_6, SWT.NONE);
		lblSubtotal.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSubtotal.setText("Subtotal:");

		txtSubtotal = new Text(composite_6, SWT.BORDER | SWT.RIGHT);
		txtSubtotal.setEditable(false);
		txtSubtotal.setText("subtotal");
		txtSubtotal.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblTax = new Label(composite_6, SWT.NONE);
		lblTax.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTax.setText("Tax:");

		txtTax = new Text(composite_6, SWT.BORDER | SWT.RIGHT);
		txtTax.setEditable(false);
		txtTax.setText("tax");
		txtTax.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblTotal = new Label(composite_6, SWT.NONE);
		lblTotal.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTotal.setText("Total:");

		txtTotal = new Text(composite_6, SWT.BORDER | SWT.RIGHT);
		txtTotal.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		txtTotal.setEditable(false);
		txtTotal.setText("total");
		txtTotal.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_6, SWT.NONE);

		Button btnFinishTransaction = new Button(composite_6, SWT.NONE);
		btnFinishTransaction.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(trans.getRevSource().size() > 0){
					try {
						trans.setCustomer_id(cust.getId());
						trans.setStore_id(store.getId());
						trans.setEmployee_id(emp.getId());
						trans.setDate(new Date());
						trans.save();
						
						//TODO for changing the status of the products
						
						
						
						PaymentWin paywin = new PaymentWin(shlSaleWindow, 0, trans);
						paywin.open();
						
						if(paywin.getWasPaid()){
	
							textCustSearch.setText("");
							txtCustName.setText("");
							txtPhone.setText("");
							txtAddress.setText("");
							txtProdSearch.setText("");
							txtProdName.setText("");
							txtPrice.setText("");
							txtSubtotal.setText("");
							txtTax.setText("");
							txtTotal.setText("");
							
							JournalEntry je = BusinessObjectDAO.getInstance().create("JournalEntry");
							je.setTransaction_id(trans.getId());
							je.setDate(new Date());
							je.save();
							
							for(int i=0;i<5;i++){
								DebitCredit dc = BusinessObjectDAO.getInstance().create("DebitCredit");
								if(i==0){
									dc.setAmount(trans.getTotal());
									dc.setGenledger_name("Cash");
									dc.setIsdebit(true);
									dc.setJournalentry_id(je.getId());
									dc.save();
								}
								else if(i==1){
									dc.setAmount(trans.getSubtotal());
									dc.setGenledger_name("Sales Revenue");
									dc.setIsdebit(false);
									dc.setJournalentry_id(je.getId());
									dc.save();
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
							}
							
							Commission comm = BusinessObjectDAO.getInstance().create("Commission");
							comm.setAmount(trans.getCommissionTotal());
							comm.setDate(new Date());
							comm.setEmployee_id(emp.getId());
							comm.setTransaction_id(trans.getId());
							comm.save();
							
							trans = BusinessObjectDAO.getInstance().create("Transaction");
							cust = null;
							prod = null;
							refreshProdTable();
						}
					} catch (Exception e1) {
						e1.printStackTrace();
						txtTax.setText("Failed: check if cust?");
					}
				}else {
					txtTotal.setText("No prods or cust");
				}
			}
		});
		btnFinishTransaction.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		btnFinishTransaction.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnFinishTransaction.setText("Finish Transaction");

	}

	/**
	 * A method to update the product table when new items are added
	 */
	private void refreshProdTable(){
		try{
			DecimalFormat df = new DecimalFormat("#.##");
			df.setMinimumFractionDigits(2);
			prodTable.setInput(trans.getRevSource());
			double tempSub = 0;
			for(RevenueSource rs: trans.getRevSource()){
				tempSub += rs.getChargeamt();
			}
			trans.setSubtotal(Double.parseDouble(df.format(tempSub)));
			trans.setTax(Double.parseDouble(df.format(tempSub * store.getSalestaxrate())));
			trans.setTotal(Double.parseDouble(df.format(trans.getSubtotal() + trans.getTax())));
			
			txtSubtotal.setText("$"+df.format(trans.getSubtotal())+"");
			txtTax.setText("$"+df.format(trans.getTax())+"");
			txtTotal.setText("$"+df.format(trans.getTotal())+"");
		}catch (Exception e){
			e.printStackTrace();
			txtSubtotal.setText("Fail on table");
		}
	}
	
	private void customerInfoWin() {
		CustomerWin custwin = new CustomerWin(shlSaleWindow, 0, cust);
		custwin.open();
		if (custwin.getCust() != null) {
			this.cust = custwin.getCust();
			txtCustName.setText(cust.getFirstname());
			txtPhone.setText(cust.getPhone());
			txtAddress.setText(cust.getAddress());
		} else if (cust != null) {
			txtCustName.setText(cust.getFirstname());
			txtPhone.setText(cust.getPhone());
			txtAddress.setText(cust.getAddress());
		} else {
			txtCustName.setText("");
			txtPhone.setText("");
			txtAddress.setText("");
		}
	}

	/**
	 * @return the emp
	 */
	public Employee getEmp() {
		return emp;
	}

	/**
	 * @param emp
	 *            the emp to set
	 */
	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	/**
	 * @return the store
	 */
	public Store getStore() {
		return store;
	}

	/**
	 * @param store
	 *            the store to set
	 */
	public void setStore(Store store) {
		this.store = store;
	}

	/**
	 * @return the cust
	 */
	public Customer getCust() {
		return cust;
	}

	/**
	 * @param cust
	 *            the cust to set
	 */
	public void setCust(Customer cust) {
		this.cust = cust;
	}
}
