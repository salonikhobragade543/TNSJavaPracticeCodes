const audio = document.getElementById('audioEl');
const albumArt = document.getElementById('albumArt');
const nowTitle = document.getElementById('nowTitle');
const nowArtist = document.getElementById('nowArtist');
const currentTimeEl = document.getElementById('currentTime');
const durationEl = document.getElementById('totalDuration');
const seekBar = document.getElementById('seekBar');
const volumeRange = document.getElementById('volumeRange');
const btnPlay = document.getElementById('btnPlay');
const btnPause = document.getElementById('btnPause');
const btnPrev = document.getElementById('btnPrev');
const btnNext = document.getElementById('btnNext');
const btnFav = document.getElementById('btnFav');
const btnShuffle = document.getElementById('btnShuffle');
const btnRepeat = document.getElementById('btnRepeat');
const badgeShuffle = document.getElementById('badgeShuffle');
const badgeRepeat = document.getElementById('badgeRepeat');
const searchInput = document.getElementById('searchInput');
const voiceBtn = document.getElementById('voiceBtn');
const playlistEl = document.getElementById('playlist');
const recentList = document.getElementById('recentList');
const favList = document.getElementById('favList');

let songs = [];
let viewSongs = [];
let idx = 0;
let shuffle = false;
let repeat = false;
let favorites = loadLS('favorites', []);
let recently = loadLS('recently', []);
const RECENT_LIMIT = 20;

/* ---------- Utilities ---------- */
function loadLS(key, fallback){ 
  try{ return JSON.parse(localStorage.getItem(key)) ?? fallback; }
  catch{ return fallback; }
}
function saveLS(key, val){ localStorage.setItem(key, JSON.stringify(val)); }

function fmtTime(sec){
  if (!Number.isFinite(sec)) return '0:00';
  const m = Math.floor(sec/60);
  const s = Math.floor(sec%60);
  return `${m}:${s.toString().padStart(2,'0')}`;
}
function setActiveRow(id){
  document.querySelectorAll('.song-row').forEach(r=>r.classList.toggle('active', r.dataset.id==id));
}

/* ---------- Rendering ---------- */
function renderPlaylist(list){
  playlistEl.innerHTML="";
  list.forEach(s=>{
    const li=document.createElement('li');
    li.className="song-row";
    li.dataset.id=s.id;
    li.innerHTML=`
      <img src="${s.albumImage}" alt="">
      <div>
        <div class="song-title">${s.title}</div>
        <div class="song-artist">${s.artist}</div>
      </div>
      <div class="song-actions">
        <button class="small-btn play-inlist">Play</button>
        <button class="small-btn fav-inlist">${favorites.includes(s.id)?'♥':'♡'}</button>
      </div>
    `;
    li.querySelector('.play-inlist').onclick=e=>{
      e.stopPropagation(); 
      playById(s.id);
    };
    li.querySelector('.fav-inlist').onclick=e=>{
      e.stopPropagation(); 
      toggleFavorite(s.id); 
      e.target.textContent=favorites.includes(s.id)?'♥':'♡'; 
      renderFavoritesMini();
    };
    li.onclick=()=>playById(s.id);
    playlistEl.appendChild(li);
  });
  if(viewSongs[idx]) setActiveRow(viewSongs[idx].id);
}
function renderFavoritesMini(){
  favList.innerHTML="";
  favorites.map(id=>songs.find(s=>s.id==id)).filter(Boolean).forEach(s=>{
    let li=document.createElement('li');
    li.innerHTML=`<img src="${s.albumImage}"><span>${s.title}</span>`;
    li.onclick=()=>playById(s.id);
    favList.appendChild(li);
  });
}
function renderRecentlyMini(){
  recentList.innerHTML="";
  recently.map(id=>songs.find(s=>s.id==id)).filter(Boolean).forEach(s=>{
    let li=document.createElement('li');
    li.innerHTML=`<img src="${s.albumImage}"><span>${s.title}</span>`;
    li.onclick=()=>playById(s.id);
    recentList.appendChild(li);
  });
}
function updateNowPlaying(s){
  nowTitle.textContent=s.title;
  nowArtist.textContent=s.artist;
  albumArt.src=s.albumImage;
  btnFav.classList.toggle('faved', favorites.includes(s.id));
  setActiveRow(s.id);
}

/* ---------- Core Playback ---------- */
function loadSongByIndex(i){
  idx=((i%viewSongs.length)+viewSongs.length)%viewSongs.length;
  const s=viewSongs[idx];
  audio.src=s.file;
  audio.load();
  updateNowPlaying(s);
}
function playById(id){
  const i=viewSongs.findIndex(x=>x.id==id);
  if(i==-1) return;
  loadSongByIndex(i);
  audio.play();
  pushRecently(viewSongs[i].id);
  renderRecentlyMini();
}
function nextSong(){
  if(shuffle){
    let r;
    do{ r=Math.floor(Math.random()*viewSongs.length); } while(r===idx);
    loadSongByIndex(r);
  } else loadSongByIndex(idx+1);
  audio.play();
}
function prevSong(){
  if(audio.currentTime>5) audio.currentTime=0;
  else loadSongByIndex(idx-1);
  audio.play();
}

/* ---------- Favorites & Recently ---------- */
function toggleFavorite(id){
  const i=favorites.indexOf(id);
  if(i==-1) favorites.push(id);
  else favorites.splice(i,1);
  saveLS('favorites',favorites);
  btnFav.classList.toggle('faved', favorites.includes(viewSongs[idx]?.id));
}
function pushRecently(id){
  recently=[id,...recently.filter(x=>x!=id)].slice(0,RECENT_LIMIT);
  saveLS('recently',recently);
}

/* ---------- Search ---------- */
function applySearch(q){
  q=q.trim().toLowerCase();
  viewSongs=!q?[...songs]:songs.filter(s=>s.title.toLowerCase().includes(q)||s.artist.toLowerCase().includes(q));
  renderPlaylist(viewSongs);
}

/* ---------- Voice Search ---------- */
function startVoice(){
  const SR=window.SpeechRecognition||window.webkitSpeechRecognition;
  if(!SR) return alert("Speech recognition not supported");
  const recog=new SR();
  recog.lang="en-US";
  recog.start();
  recog.onresult=e=>{
    const txt=e.results[0][0].transcript;
    searchInput.value=txt;
    applySearch(txt);
  };
}

/* ---------- Time/Seek/Volume ---------- */
audio.onloadedmetadata=()=>{
  durationEl.textContent=fmtTime(audio.duration);
  seekBar.value=0;
};
audio.ontimeupdate=()=>{
  currentTimeEl.textContent=fmtTime(audio.currentTime);
  if(audio.duration) seekBar.value=(audio.currentTime/audio.duration)*100;
};
seekBar.oninput=()=>{
  if(audio.duration) audio.currentTime=(seekBar.value/100)*audio.duration;
};
volumeRange.oninput=()=>{ audio.volume=Number(volumeRange.value); };
audio.onended=()=>{
  if(viewSongs[idx]) pushRecently(viewSongs[idx].id);
  renderRecentlyMini();
  if(repeat){ audio.currentTime=0; audio.play(); }
  else nextSong();
};

/* ---------- Buttons & Events ---------- */
btnPlay.onclick=()=>{ audio.play(); btnPlay.style.display="none"; btnPause.style.display="inline-block"; };
btnPause.onclick=()=>{ audio.pause(); btnPause.style.display="none"; btnPlay.style.display="inline-block"; };
audio.onplay=()=>{ albumArt.classList.add("playing"); btnPlay.style.display="none"; btnPause.style.display="inline-block"; };
audio.onpause=()=>{ albumArt.classList.remove("playing"); btnPause.style.display="none"; btnPlay.style.display="inline-block"; };

btnNext.onclick=nextSong;
btnPrev.onclick=prevSong;
btnFav.onclick=()=>toggleFavorite(viewSongs[idx].id);
btnShuffle.onclick=()=>{ shuffle=!shuffle; badgeShuffle.textContent=`Shuffle: ${shuffle?'On':'Off'}`; };
btnRepeat.onclick=()=>{ repeat=!repeat; badgeRepeat.textContent=`Repeat: ${repeat?'On':'Off'}`; };
searchInput.oninput=e=>applySearch(e.target.value);
voiceBtn.onclick=startVoice;

/* ---------- Load Songs ---------- */
async function boot(){
  try{
    const res=await fetch("data/songs.json");
    songs=await res.json();
    viewSongs=[...songs];
    renderPlaylist(viewSongs);
    renderFavoritesMini();
    renderRecentlyMini();
    loadSongByIndex(0);
    audio.volume=Number(volumeRange.value);
  }catch(e){
    console.error("Error loading songs.json",e);
    playlistEl.innerHTML="<li>Error loading songs.json</li>";
  }
}
boot();

function updateNowPlaying(s){
  nowTitle.textContent = s.title;
  nowArtist.textContent = s.artist;
  albumArt.src = s.albumImage;
  btnFav.classList.toggle('faved', favorites.includes(s.id));
  setActiveRow(s.id);

  // 🌈 Dynamic blurred background update
  const bg = document.querySelector('.bg-blur');
  if (bg) bg.style.backgroundImage = `url(${s.albumImage})`;
}

audio.onplay = () => {
  albumArt.classList.add("rotating");
  btnPlay.style.display = "none";
  btnPause.style.display = "inline-block";
};

audio.onpause = () => {
  albumArt.classList.remove("rotating");
  btnPause.style.display = "none";
  btnPlay.style.display = "inline-block";
};

