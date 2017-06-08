package Project.Services;

import Project.Dao.InvoiceDao;
import Project.Dao.OwnerDao;
import com.S63B.domain.Entities.Invoice;
import com.S63B.domain.Entities.Owner;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Stan
 */
@Service("invoiceService")
public class InvoiceService {

	private InvoiceDao invoiceDao;
	private OwnerDao ownerDao;

	@Autowired
	private InvoiceService(InvoiceDao invoiceDao, OwnerDao ownerDao) {
		this.invoiceDao = invoiceDao;
		this.ownerDao = ownerDao;
	}

	public List<Invoice> getInvoicesFromOwner(Owner owner) {
		return invoiceDao.getByOwner(owner);
	}

	public Invoice createInvoice(int owner_id, double price, DateTime startDate, DateTime endDate, String countryOfOrigin) {
		Invoice invoice = new Invoice(ownerDao.findOne(owner_id), new DateTime(), price, startDate, endDate, 0, countryOfOrigin);
		invoiceDao.save(invoice);
		return invoice;
	}

	public Invoice getInvoiceById(int id) {
		return invoiceDao.findOne(id);
	}

	public Invoice saveInvoice(Invoice invoice){
		invoiceDao.save(invoice);
		return invoice;
	}

}
