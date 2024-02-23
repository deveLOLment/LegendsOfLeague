package com.project.legendsofleague.domain.rate.repository;

import com.project.legendsofleague.domain.rate.domain.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepository extends JpaRepository<Rate, Long> {
}
