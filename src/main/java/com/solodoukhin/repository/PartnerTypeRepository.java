package com.solodoukhin.repository;

import com.solodoukhin.model.PartnerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Solodoukhin Viktor
 * Date: 14.07.18
 * Description: TODO
 */
@Repository
public interface PartnerTypeRepository extends JpaRepository<PartnerType, Integer> {}
