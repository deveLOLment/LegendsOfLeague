package com.project.legendsofleague.domain.order.domain;

import com.project.legendsofleague.domain.member.domain.Member;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  private LocalDateTime orderDate;

  private String orderCode; //UUID로 부여

  private Integer totalOrderPrice; //전체 주문 가격
}
