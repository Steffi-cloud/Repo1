
package com.yourorg.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yourorg.payment.model.PaymentDetails;

//@Repository
/**git remote -v

 * git push -u origin main
 * 
 * git remote -v                   # Confirm remote URL
git branch                      # Confirm you're on 'main'
git push -u origin main         # Push code to GitHub
git remote remove origin
git remote add origin https://github.com/Steffi-cloud/Repo1.git


git push -u origin main


 */

public interface PaymentRepository extends JpaRepository< PaymentDetails,Integer> {

}
