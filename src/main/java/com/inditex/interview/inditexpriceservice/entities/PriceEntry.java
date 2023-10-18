package com.inditex.interview.inditexpriceservice.entities;

import com.inditex.interview.inditexpriceservice.Currency;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prices")
public class PriceEntry {
  public static final String DATE_TIME_PATTERN = "yyyy-MM-dd-HH.mm.ss";
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "price_list")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "brand_id")
  private Brand brand;

  private double price;

  private int priority;

  private long productId;

  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime startDate;

  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime endDate;

  @Enumerated(EnumType.STRING)
  private Currency currency;

  // Custom method for converting between database format and LocalDateTime
  public void setStartDateFromString(String dateString) {
    this.startDate =
        LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
  }

  public String getStartDateAsString() {
    return startDate.format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
  }

  public void setEndDateFromString(String dateString) {
    this.endDate =
        LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
  }

  public String getEndDateAsString() {
    return endDate.format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
  }
}
