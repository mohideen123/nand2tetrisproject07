load Add01.vm,
output-file Add01.cmp,
//compare-to SimpleAdd.cmp,
output-list RAM[0]%D2.6.2 RAM[256]%D2.6.2;

set RAM[0] 256,

repeat 30 {
  vmstep;
}

output;
