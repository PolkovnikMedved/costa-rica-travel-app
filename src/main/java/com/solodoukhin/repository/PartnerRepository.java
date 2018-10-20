package com.solodoukhin.repository;

import com.solodoukhin.model.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author: Solodoukhin Viktor
 * Date: 14.07.18
 * Description: TODO
 */
@Repository
public interface PartnerRepository extends JpaRepository<Partner, Integer> {

    List<Partner> getAllByCountry(String country);
}
