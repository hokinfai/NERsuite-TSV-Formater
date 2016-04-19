# NERsuite-dictionary-tagger-result-formatted
A program used for formatting two different text mining results including result.features.txt from NERsuitte and TSV from Argo.

The input format for result.features.txt from NERsuitte has to be kept as the following:
"BeginningIndex <tab> EndingIndex <tab> terms <tab> terms in original form <tab> annotation <tab> annotation"
"0	3	206	206	CD	B-NP	O	O"
"4	13	FIELDIANA	FIELDIANA	NN	I-NP	O	O"
"13	14	:	:	:	O	O	O"
"15	21	BOTANY	BOTANY	NN	B-NP	O	O"
"21	22	,	,	,	O	O	O"
"23	29	VOLUME	VOLUME	NN	B-NP	O	O"
"30	32	24	24	CD	I-NP	O	O"

The input format for TSV from Argo:
"1	_InitialView	/Volumes/Data/Projects/MiningBiodiversity/corpus/raw_paginated/19701_English_2376386_1958.txt	AnatomicalEntity	branches	34	42"
"1	_InitialView	/Volumes/Data/Projects/MiningBiodiversity/corpus/raw_paginated/19701_English_2376386_1958.txt	Quality	dull pale red	48	61"
"1	_InitialView	/Volumes/Data/Projects/MiningBiodiversity/corpus/raw_paginated/19701_English_2376386_1958.txt	Quality	10-20 cm. long	63	77"
