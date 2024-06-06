package tera.daniel.instapayws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tera.daniel.instapayws.bean.BancNetBean;

public interface BancNetRepository extends JpaRepository<BancNetBean, String> {
	public BancNetBean getByBicfi(String bicfi);
}
