load Add01.asm,
output-file Add01.out,
compare-to Add01.cmp,
output-list RAM[0]%D2.6.2 RAM[256]%D2.6.2;

set RAM[0] 256,

repeat 60 {
  ticktock;
}

output;