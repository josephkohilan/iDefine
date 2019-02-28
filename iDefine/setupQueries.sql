CREATE SCHEMA ievolve;

use ievolve;

DROP TABLE IF EXISTS `ievolve_master_table`;
CREATE TABLE IF NOT EXISTS `ievolve_master_table` (
  `definition_id` int(10) NOT NULL AUTO_INCREMENT,
  `keyword` varchar(50) NOT NULL,
  `category` varchar(25) DEFAULT NULL,
  `definition` varchar(500) NOT NULL,
  `approval_status` binary(1) NOT NULL,
  `up_votes` int(11) NOT NULL,
  `down_votes` int(11) NOT NULL,
  `created_date` date DEFAULT NULL,
  PRIMARY KEY (`definition_id`),
  UNIQUE KEY `definition_id` (`definition_id`)
) ENGINE=MyISAM AUTO_INCREMENT=213 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ievolve_master_table`
--

INSERT INTO `ievolve_master_table` (`definition_id`, `keyword`, `category`, `definition`, `approval_status`, `up_votes`, `down_votes`, `created_date`) VALUES
(131, 'rendering provider', 'BD', 'Provider who has actually rendered healthcare services to the member eventhough the billing provider for the claim is different.', 0x31, 0, 0, '2018-11-27'),
(128, 'pre-authorization', 'BD', 'Pre authorization is obtaining permission to render the service prior the service is provided. For certain services payer expects member/provider to seek advance approval. Services which involves some sort of risk, or high dollar expenses.', 0x31, 0, 0, '2018-11-27'),
(129, 'procedure bits', 'BD', 'It is the logical group of procedure codes from the Premier Bits Database', 0x31, 0, 0, '2018-11-27'),
(130, 'billing provider', 'BD', 'Provider who is billing for the services present in the caim which were provided for a member/ subscriber.', 0x31, 0, 0, '2018-11-27'),
(127, 'unsolicited provider', 'BD', 'Unsolicited means uninvited. HCSC don’t approach Providers with particular type and specialty for contract. Such providers remain non contracted. As HCSC never reached out to the provider for contract, they are referred as unsolicited.', 0x31, 0, 0, '2018-11-27'),
(123, 'batch process,non adj coc process', 'Accums', 'The batch process is run at the start of the benefit period after the member has a finalized claim and if the COC indicator is Yes. The batch process calculates the OPX/DED utilization for that member during the Lifetime period of the previous year.', 0x31, 0, 0, '2018-11-27'),
(124, 'coc adj flow', 'Accums', '2018 year has COC Indicator as Yes. A 2017 claim comes in 2018  which falls in the lookback period. The 2017 claim will be proccesed as normal and the OPX/DED utilization will be upadted for 2017 and also for 2018 record in the ledger as the claims falls within the lookback period.', 0x31, 0, 0, '2018-11-27'),
(125, 'accums ledger', 'Accums', 'Ledger tracks the utilization and limits for each of the cost share for that member. It tracks the limit the Limit, Utilization and Amount Remaining for each of the cost share. It also tracks the HCSC contribution for each service line. Ledger contains 3 tables - Header, Event and Summary Table.', 0x31, 2, 1, '2018-12-06'),
(122, 'event table', 'Accums', 'For COC, when a batch process is run for a member, a record is created in the event table which signifies that a batch process has been run for that particular member for that year.', 0x31, 0, 0, '2018-11-27'),
(114, 'dme', 'Accums', 'DME (durable medical equipment) is any physician ordered equipment providing therapeutic benefit to a patient. DME is provided to a member based on medical condition(s) and/or illness; examples include wheelchairs and hearing aids. The equipment can be either purchased (smaller priced items) or rented (larger priced items); claims are submitted for both. Generally in a rental scenario, the sum of a members rental claims, should never exceed the cost to purchase the DME.', 0x31, 0, 0, '2018-11-27'),
(121, 'coc', 'Accums', 'Example: Year 2017, OPX utilization of the last quater is $200. Current year - 2019. OPX utilization is $300. 2018 has COC indicator as yes. The $200 OPX utilization will eb rolled over to the 2018 OPX utilization. So OPX 2018 utilization will be $500', 0x31, 0, 0, '2018-11-27'),
(119, 'slaa', 'Accums', 'Service Line allowed Amount. This is the eligible amount for each service line that comes from pricing.', 0x31, 0, 0, '2018-11-27'),
(120, 'coc', 'Accums', 'Carry Over Credit. COC is applicable only for OPX and DED. In case of COC, the OPX or DED utilization of the previous years during the lifetime period (generally last quarter of the previous year) will be added to the current years;s OPX/DED utilization.', 0x31, 0, 0, '2018-11-27'),
(118, 'att', 'Accums', 'Amount to Take. It is the member utilization that is trcaked in the ledger for each of the cost share. \r\nATT =  mim (SLAAR, amount remaining of activated cost share, amount remaining of associated costs share)', 0x31, 0, 0, '2018-11-27'),
(117, 'overage amount,', 'Accums', 'Example: Activated cost share is Copay with Always Applies Indicator is Yes. The Associated cost share is OPX. The limit of OPX is 500 and the current utilization is 450. A new claim comes with copay amount of $100. In this case, $50 will be added to OPX bucket and the remaining $50 will be added to the Overage OPX bucket since copay is always applies.', 0x31, 0, 0, '2018-11-27'),
(126, 'provider scope of practice', 'BD', 'Depending on provider type and specialty, payer specifies the scope if provider’s practice. Only service which are part of provider’s practice will be paid, rest will be denied.', 0x31, 0, 0, '2018-11-27'),
(116, 'overage amount', 'Accums', 'When the limit of a particular cost share is met and the activated cost share has an Always Applies Indicator as Yes, the utilization of the activated cost shares will feed into the overage bucket after the limit of the associated cost share has been met.', 0x31, 0, 0, '2018-11-27'),
(115, 'lifetime period', 'Accums', 'For DME claims the lifetime perios is usually 5 years. ERP is the only Group which has an exception of 3 years lifetime period. Lifetime period is basically the shelf life of the DME equipment. The rental price should not exceed the purchase price during the lifetime period.', 0x31, 0, 0, '2018-11-27'),
(132, 'adjusted claim', 'BD', 'The process of correcting over paid or under paid claim is called as benefit adjustment, The claim which is created to adjust the benefit is called as adjusted claim.', 0x31, 0, 0, '2018-11-27'),
(133, 'local claim', 'BD', 'Claim submitted for the service rendered in member’s home state(state were member is enrolled).', 0x31, 0, 0, '2018-11-27'),
(134, 'out of country claim', 'BD', 'As BCBS provides coverage out of country as well, it’s possible to receive Out of country claim.', 0x31, 0, 0, '2018-11-27'),
(135, 'grace period', 'BD', 'It is the extended time period up to which the claim would not be denied after the premium is due.', 0x31, 0, 0, '2018-11-27'),
(136, 'timely filing', 'BDM', 'Time limit specified in the contract to file the claim. Claim submitted after timely filing period would deny for timely filing.', 0x31, 0, 0, '2018-11-27'),
(137, 'service category', 'BDM', 'Grouping the vast set of medical procedures into a smaller set of groups that are manageable for the health plan.', 0x31, 0, 0, '2018-11-27'),
(138, 'network exception', 'BDM', 'Under certain circumstances we need to give in network benefit to out of network providers, such as Emergency service, Unsolicited provider.', 0x31, 0, 0, '2018-11-27'),
(141, 'diagnosis-related group, drg', 'BDM', 'An inpatient classification used to pay the hospital or other provider for their services and to categorize illness by diagnosis and treatment.', 0x31, 0, 0, '2018-11-27'),
(142, 'provider network', 'BDM', 'The group of physicians, hospitals, and other medical care professionals that a managed care plan has contracted with to deliver medical services to its members.', 0x31, 0, 0, '2018-11-27'),
(199, 'cost share, deductible', NULL, 'Deductible is the amount you must pay for a health care service before your health insurance plan begins paying. For example, if you visit an emergency department and your deductible amount is $500, you must pay the $500 deductible amount before your insurance company will cover the remaining health care charges associated with your emergency visit.', 0x31, 1, 0, '2018-12-06'),
(145, 'coinsurance', 'BDM', 'Coinsurance refers to your share of the costs of a health care visit. Coinsurance is calculated as a percentage of the amount of a service. You are responsible for paying the full amount of your coinsurance and your deductible charge.', 0x31, 0, 0, '2018-11-27'),
(146, 'coinsurance', 'BDM', 'Example: If your health insurance plan allows $100 for a health care checkup and you have paid your deductible, your coinsurance payment of 20 percent (or whatever percent applies to your insurance plan) would be $20. Your health insurance plan will pay the remaining $80 due for your visit.', 0x31, 0, 0, '2018-11-27'),
(147, 'out of pocket maximum, opx', 'BDM', 'Out Of Pocket Maximum is the most you have to pay for covered services in a plan year. After you spend this amount on deductibles, copayments, and coinsurance, your health plan pays 100% of the costs of covered benefits.', 0x31, 0, 0, '2018-11-27'),
(148, 'out of pocket maximum, opx', 'BDM', 'The out-of-pocket limit doesn\'t include your monthly premiums. It also doesn\'t include anything you spend for services your plan doesn\'t cover.', 0x31, 0, 0, '2018-11-27'),
(198, 'cost share, deductible', NULL, 'Deductibles do not always apply to all health care services. For this reason and to avoid unwanted billing surprises, you should ask your insurance company for a list of covered services', 0x31, 3, 1, '2018-12-06'),
(150, 'premier output data', 'Core Adj', 'Premier determines the Reimbursement for the claim using the values passed by Blue Chip to determine the following.\r\nExamples of PREMIER Output data:\r\n•Claim PREMIER Return Code\r\n•Claim Allowed Amount\r\n•HPI\r\n•Total Claim Allowed Amount\r\n•DRG Code\r\n•DRG Version\r\n•DRG Grouper Version\r\n•DRG Weight Version', 0x31, 0, 0, '2018-11-27'),
(151, 'npi crosswalk, npi crosswalk search', 'Core Adj', 'It is the link established between the National Provider Identifier (NPI), the Provider File Index Number (PFIN) given by Payer, and the Contractor Code of the Provider which have been assigned by Medicare.', 0x31, 0, 0, '2018-11-27'),
(152, 'eep, eligibility enhancement project', 'Core Adj', 'It is the first step in the project to modernize the Blue Chip claim processing system.\nIt consists of  Eligibility screens for the Blue Chip system, and ODS Member Inquiry, Provider Inquiry, & Locks GUI applications.', 0x31, 0, 0, '2018-11-27'),
(153, 'claim lock', 'Core Adj', 'A lock is an automated flag placed on a claim or group of claims that is based on a set of conditions.\nLocks can be the result of a system defect or limitation, or used for special processing circumstances related to business decisions or group intention.', 0x31, 0, 0, '2018-11-27'),
(154, 'pme', 'Core Adj', 'Provider Match & Eligibility. This process flow is a part of Project Keystone, which will be replacing the EEP for Provider inquiry while processing a claim.', 0x31, 0, 0, '2018-11-27'),
(155, 'mme', 'Core Adj', 'Member Match & Eligibility. This process flow is a part of Project Keystone, which will be replacing the EEP for Member inquiry while processing a claim.', 0x31, 0, 0, '2018-11-27'),
(156, 'cvp, convergence point', 'Core Adj', 'This is an auditing software tool, used to enhance the affordability of care and realize medical savings. Accuracy in Auditing is assured by reading claims history.', 0x31, 0, 0, '2018-11-27'),
(157, 'ccp, cost containment products', 'Core Adj', 'Health Insurance products designed to contain cost or elimintate certain charges from the benefit plan of the Members on Group accounts and Individual accounts are known as Cost Containment products. There are about 20 such CCP offered by HCSC.', 0x31, 0, 0, '2018-11-27'),
(158, 'passion', 'Core Adj', 'PASSION stands for Pre Authorization Simplification Strategy Integration Optimization Nimbleness. \nThis functionality will track a patient’s use of pre-authorized services in the form of units. Passion is removing the matching point system Blue Chip has in place. Passion takes five components through the matching process.', 0x31, 0, 0, '2018-11-27'),
(159, 'premier pricing', 'Core Adj', 'Blue Chip Pricing obtains values from GCPS which determine how claims will be priced, either in Premier or in Blue Chip. These are the pricing values obtained by Blue Chip Pricing from GCPS and passed to Premier Pricer via the Premier interface.', 0x31, 0, 0, '2018-11-27'),
(200, 'cost share, coinsurance', NULL, 'Coinsurance refers to your share of the costs of a health care visit. Coinsurance is calculated as a percentage of the amount of a service. You are responsible for paying the full amount of your coinsurance and your deductible charge.', 0x31, 1, 0, '2018-12-06'),
(201, 'cost share, coinsurance', NULL, 'Coinsurance Example: If your health insurance plan allows $100 for a health care checkup and you have paid your deductible, your coinsurance payment of 20 percent (or whatever percent applies to your insurance plan) would be $20. Your health insurance plan will pay the remaining $80 due for your visit.', 0x31, 0, 0, '2018-12-06'),
(202, 'deductible', NULL, 'Deductible. The amount you pay for covered health care services before your insurance plan starts to pay. With a $2,000 deductible, for example, you pay the first $2,000 of covered services yourself. After you pay your deductible, you usually pay only a copayment or coinsurance for covered services.', 0x30, 0, 0, '2018-12-06');
COMMIT;

