package tera.daniel.instapayws.bean;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tera.daniel.instapayws.exception.BankSignException;
import tera.daniel.instapayws.repository.BancNetRepository;

@RestController
public class BancNetController {
	
	@Autowired
	BancNetRepository repository;
	
	final String PASS = "93c4f6689447bab9aa2d5f0f3f5c9a696547982a1614881f6bb0bd5e8301861b12c07b80819f40b8e9632922225dd5e6ac88b1c653952916e8d2669ab74ade05";
	final String USR_ID = "VCL_USR";
	final String SYS_CODE = "VCL01";
	
	@GetMapping("bank/status-in-ips/{bicfi}")
	public ResponseEntity<BancNetBean> getBParticipatingBank(@PathVariable("bicfi") String id) {
		BancNetBean bean = 
				//repository.getReferenceById(id);
		repository.getByBicfi(id);
		return new ResponseEntity<>(bean,null,HttpStatus.OK);
	}
	
	/*
	 * @PostMapping(path = "bank/sign-off",consumes = "application/json") public
	 * ResponseEntity<BancNetBean> bankSignOf(@RequestBody BancNetBean rqBean)
	 * throws BankSignException { BancNetBean bean =
	 * repository.getByBicfi(rqBean.getBicfi()); if( "off".equals(bean.getStatus())
	 * ){ throw new BankSignException("Bank is already signed-off!"); }
	 * 
	 * bean.setStatus("off"); repository.save(bean); return new
	 * ResponseEntity<>(bean,null,HttpStatus.OK); }
	 */
	
	@PostMapping(path = "bank/api/v1/status/sign-on-off",consumes = "application/json")
	public ResponseEntity<SignResponseBean> bankSignOn(HttpServletRequest request, @RequestBody BancNetBean rqBean) throws BankSignException {
		//sys cred
		String channelId=request.getHeader("channelId");
		String userId=request.getHeader("userId");
		String password=request.getHeader("password");
		
		//bank
		boolean isSignOn = rqBean.getIsSignOn();
		String bank = rqBean.getBicfi();
		String updateBy = rqBean.getUpdateBy();
		
		//responseBean
		SignResponseBean responseBean = new SignResponseBean();
		responseBean.setStatus("1");
		responseBean.setBusinessMsgId("testMessageID01");
		BancNetBean bean = repository.getByBicfi(bank);
		if(bean==null) {
			throw new BankSignException("Bank is not recognized!");
		}
		if(USR_ID.equals(userId) && PASS.equals(password) && SYS_CODE.equals(channelId)){
			//sign on
			if(isSignOn) {
				if( "on".equals(bean.getStatus()) ){
					throw new BankSignException("Bank is already signed-on!");
				}
				bean.setStatus("on");
			}
			//sign off
			else {
				if( "off".equals(bean.getStatus()) ){
					throw new BankSignException("Bank is already signed-off!");
				}
				bean.setStatus("off");
			}
			bean.setUpdateBy(updateBy);
			
		}else {
			responseBean.setStatus("0");
			responseBean.setErrorMessage("Invalid credentials");
			return new ResponseEntity<>(responseBean,null,HttpStatus.OK);
		}
		
		repository.save(bean);
		responseBean.setErrorMessage("none");
		return new ResponseEntity<>(responseBean,null,HttpStatus.OK);
	}
}
