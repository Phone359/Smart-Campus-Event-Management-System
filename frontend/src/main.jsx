import React, { useMemo, useState } from 'react';
import { createRoot } from 'react-dom/client';
import {
  AlertCircle,
  CalendarPlus,
  CheckCircle2,
  ClipboardCheck,
  LayoutDashboard,
  LogIn,
  LogOut,
  ShieldCheck,
  UserPlus,
  Users,
} from 'lucide-react';
import './styles.css';

const users = [
  { username: 'admin', password: '1234', role: 'ADMIN', name: 'Admin User' },
  { username: 'organizer1', password: 'pass', role: 'ORGANIZER', name: 'Event Organizer' },
  { username: 'student1', password: 'pass', role: 'STUDENT', name: 'Student One' },
  { username: 'student2', password: 'pass', role: 'STUDENT', name: 'Student Two' },
];

const starterEvents = [
  {
    id: 1,
    title: 'Tech Workshop',
    category: 'Technology',
    date: '2026-05-12',
    location: 'Innovation Lab',
    capacity: 1,
    registered: [],
  },
  {
    id: 2,
    title: 'AI Seminar',
    category: 'Research',
    date: '2026-05-18',
    location: 'Main Auditorium',
    capacity: 30,
    registered: ['student1'],
  },
  {
    id: 3,
    title: 'Career Readiness Clinic',
    category: 'Student Life',
    date: '2026-05-22',
    location: 'Student Center',
    capacity: 24,
    registered: [],
  },
];

function canCreateEvent(user) {
  return user && ['ADMIN', 'ORGANIZER'].includes(user.role);
}

function canRegisterForEvent(user) {
  return user?.role === 'STUDENT';
}

function App() {
  const [currentUser, setCurrentUser] = useState(null);
  const [events, setEvents] = useState(starterEvents);
  const [activeView, setActiveView] = useState('events');
  const [loginForm, setLoginForm] = useState({ username: '', password: '' });
  const [eventForm, setEventForm] = useState({
    title: '',
    category: 'Academic',
    date: '2026-05-25',
    location: '',
    capacity: 20,
  });
  const [notice, setNotice] = useState(null);
  const [auditLog, setAuditLog] = useState([
    { actor: 'system', action: 'READY', result: 'Prototype initialized' },
  ]);

  const stats = useMemo(() => {
    const totalSeats = events.reduce((sum, event) => sum + Number(event.capacity), 0);
    const registeredSeats = events.reduce((sum, event) => sum + event.registered.length, 0);
    const myRegistrations = currentUser
      ? events.filter((event) => event.registered.includes(currentUser.username)).length
      : 0;

    return {
      totalEvents: events.length,
      registeredSeats,
      openSeats: Math.max(totalSeats - registeredSeats, 0),
      myRegistrations,
    };
  }, [currentUser, events]);

  function record(actor, action, result) {
    setAuditLog((entries) => [
      { actor: actor || 'anonymous', action, result },
      ...entries.slice(0, 9),
    ]);
  }

  function showNotice(type, text) {
    setNotice({ type, text });
  }

  function handleLogin(event) {
    event.preventDefault();

    const username = loginForm.username.trim();
    const password = loginForm.password.trim();

    if (!username || !password) {
      record('anonymous', 'LOGIN', 'Rejected empty username or password');
      showNotice('error', 'Please enter both username and password.');
      return;
    }

    const user = users.find((item) => item.username === username);
    if (!user || user.password !== password) {
      record(username, 'LOGIN', 'Invalid username or password');
      showNotice('error', 'Invalid username or password.');
      return;
    }

    setCurrentUser(user);
    setActiveView(user.role === 'STUDENT' ? 'events' : 'manage');
    setLoginForm({ username: '', password: '' });
    record(user.username, 'LOGIN', 'Login successful');
    showNotice('success', `Welcome, ${user.name}. You are logged in as ${user.role}.`);
  }

  function handleLogout() {
    record(currentUser?.username, 'LOGOUT', 'User signed out');
    setCurrentUser(null);
    setActiveView('events');
    setNotice(null);
  }

  function handleCreateEvent(event) {
    event.preventDefault();

    if (!canCreateEvent(currentUser)) {
      record(currentUser?.username, 'CREATE_EVENT', 'Access denied');
      showNotice('error', 'Access denied: only admins or organizers can create events.');
      return;
    }

    const title = eventForm.title.trim();
    const location = eventForm.location.trim();
    const capacity = Number(eventForm.capacity);

    if (!title || !location || !eventForm.date || capacity < 1) {
      record(currentUser.username, 'CREATE_EVENT', 'Rejected missing event fields');
      showNotice('error', 'Please enter title, location, date, and capacity.');
      return;
    }

    if (events.some((item) => item.title.toLowerCase() === title.toLowerCase())) {
      record(currentUser.username, 'CREATE_EVENT', 'Duplicate event title');
      showNotice('error', 'Event already exists.');
      return;
    }

    const nextEvent = {
      id: Date.now(),
      title,
      category: eventForm.category,
      date: eventForm.date,
      location,
      capacity,
      registered: [],
    };

    setEvents((items) => [nextEvent, ...items]);
    setEventForm({ title: '', category: 'Academic', date: '2026-05-25', location: '', capacity: 20 });
    record(currentUser.username, 'CREATE_EVENT', `Event created: ${nextEvent.title}`);
    showNotice('success', 'Event created successfully.');
    setActiveView('events');
  }

  function handleRegister(eventId) {
    const selectedEvent = events.find((event) => event.id === eventId);

    if (!canRegisterForEvent(currentUser)) {
      record(currentUser?.username, 'REGISTER_EVENT', 'Access denied');
      showNotice('error', 'Access denied: only students can register for events.');
      return;
    }

    if (!selectedEvent) {
      record(currentUser.username, 'REGISTER_EVENT', 'Event not found');
      showNotice('error', 'Event not found.');
      return;
    }

    if (selectedEvent.registered.includes(currentUser.username)) {
      record(currentUser.username, 'REGISTER_EVENT', 'Duplicate registration');
      showNotice('error', 'You are already registered for this event.');
      return;
    }

    if (selectedEvent.registered.length >= selectedEvent.capacity) {
      record(currentUser.username, 'REGISTER_EVENT', 'Event full');
      showNotice('error', 'This event is already full. Please choose another event.');
      return;
    }

    setEvents((items) =>
      items.map((event) =>
        event.id === eventId
          ? { ...event, registered: [...event.registered, currentUser.username] }
          : event
      )
    );
    record(currentUser.username, 'REGISTER_EVENT', `Registration successful: ${selectedEvent.title}`);
    showNotice('success', 'Registration successful.');
  }

  if (!currentUser) {
    return (
      <LoginScreen
        loginForm={loginForm}
        notice={notice}
        onChange={setLoginForm}
        onLogin={handleLogin}
      />
    );
  }

  return (
    <main className="app-shell">
      <header className="topbar">
        <div>
          <p className="eyebrow">Smart Campus Event Management</p>
          <h1>Dashboard</h1>
        </div>
        <div className="user-badge">
          <ShieldCheck size={20} />
          <div>
            <span>{currentUser.name}</span>
            <strong>{currentUser.role}</strong>
          </div>
          <button className="icon-button" onClick={handleLogout} aria-label="Sign out" title="Sign out">
            <LogOut size={18} />
          </button>
        </div>
      </header>

      {notice && <Notice notice={notice} />}

      <nav className="tabbar" aria-label="Dashboard sections">
        <button className={activeView === 'events' ? 'active' : ''} onClick={() => setActiveView('events')}>
          <LayoutDashboard size={18} />
          Events
        </button>
        <button className={activeView === 'manage' ? 'active' : ''} onClick={() => setActiveView('manage')}>
          <CalendarPlus size={18} />
          Manage
        </button>
        <button className={activeView === 'audit' ? 'active' : ''} onClick={() => setActiveView('audit')}>
          <ClipboardCheck size={18} />
          Audit
        </button>
      </nav>

      <section className="dashboard-grid" aria-label="Dashboard summary">
        <SummaryCard icon={<ClipboardCheck />} label="Events" value={stats.totalEvents} />
        <SummaryCard icon={<Users />} label="Registered Seats" value={stats.registeredSeats} />
        <SummaryCard icon={<CalendarPlus />} label={currentUser.role === 'STUDENT' ? 'My Events' : 'Open Seats'} value={currentUser.role === 'STUDENT' ? stats.myRegistrations : stats.openSeats} />
      </section>

      {activeView === 'events' && (
        <EventsView currentUser={currentUser} events={events} onRegister={handleRegister} />
      )}

      {activeView === 'manage' && (
        <ManageView
          currentUser={currentUser}
          eventForm={eventForm}
          events={events}
          onCreateEvent={handleCreateEvent}
          onEventFormChange={setEventForm}
        />
      )}

      {activeView === 'audit' && <AuditView auditLog={auditLog} />}
    </main>
  );
}

function LoginScreen({ loginForm, notice, onChange, onLogin }) {
  return (
    <main className="login-shell">
      <section className="login-hero">
        <p className="eyebrow">IT368 Software Engineering Prototype</p>
        <h1>Smart Campus Event Management</h1>
        <p>
          Login first, then access the dashboard. The dashboard changes logically based on the
          role: student, organizer, or admin.
        </p>
      </section>

      <section className="login-card">
        <div className="panel-heading">
          <div>
            <p className="eyebrow">Secure Access</p>
            <h2>Login</h2>
          </div>
          <LogIn size={24} className="soft-icon" />
        </div>

        {notice && <Notice notice={notice} />}

        <form className="form-stack" onSubmit={onLogin}>
          <label>
            Username
            <input
              value={loginForm.username}
              onChange={(event) => onChange((form) => ({ ...form, username: event.target.value }))}
              placeholder="student1"
              autoComplete="username"
            />
          </label>
          <label>
            Password
            <input
              value={loginForm.password}
              onChange={(event) => onChange((form) => ({ ...form, password: event.target.value }))}
              placeholder="pass"
              type="password"
              autoComplete="current-password"
            />
          </label>
          <button className="primary-button" type="submit">
            <LogIn size={18} />
            Login to Dashboard
          </button>
        </form>

        <div className="account-list" aria-label="Demo accounts">
          <strong>Demo accounts</strong>
          <button type="button" onClick={() => onChange({ username: 'student1', password: 'pass' })}>
            Student: student1 / pass
          </button>
          <button type="button" onClick={() => onChange({ username: 'organizer1', password: 'pass' })}>
            Organizer: organizer1 / pass
          </button>
          <button type="button" onClick={() => onChange({ username: 'admin', password: '1234' })}>
            Admin: admin / 1234
          </button>
        </div>
      </section>
    </main>
  );
}

function EventsView({ currentUser, events, onRegister }) {
  return (
    <section className="event-section">
      <div className="section-heading">
        <div>
          <p className="eyebrow">Event Registration</p>
          <h2>Available Events</h2>
        </div>
        <span className="role-chip">{currentUser.role === 'STUDENT' ? 'Student registration enabled' : 'View only for this role'}</span>
      </div>

      <div className="event-grid">
        {events.map((event) => {
          const remaining = event.capacity - event.registered.length;
          const isRegistered = event.registered.includes(currentUser.username);
          return (
            <article className="event-card" key={event.id}>
              <div className="event-topline">
                <span>{event.category}</span>
                <strong>{remaining} seats left</strong>
              </div>
              <h3>{event.title}</h3>
              <dl>
                <div>
                  <dt>Date</dt>
                  <dd>{event.date}</dd>
                </div>
                <div>
                  <dt>Location</dt>
                  <dd>{event.location}</dd>
                </div>
                <div>
                  <dt>Capacity</dt>
                  <dd>
                    {event.registered.length}/{event.capacity}
                  </dd>
                </div>
              </dl>
              <button
                className={isRegistered ? 'secondary-button' : 'primary-button'}
                onClick={() => onRegister(event.id)}
              >
                <UserPlus size={18} />
                {isRegistered ? 'Already Registered' : 'Register'}
              </button>
            </article>
          );
        })}
      </div>
    </section>
  );
}

function ManageView({ currentUser, eventForm, events, onCreateEvent, onEventFormChange }) {
  return (
    <section className="workspace-grid">
      <div className="panel">
        <div className="panel-heading">
          <div>
            <p className="eyebrow">Admin / Organizer</p>
            <h2>Create Event</h2>
          </div>
          <CalendarPlus size={22} className="soft-icon" />
        </div>

        {!canCreateEvent(currentUser) && (
          <div className="locked-message">
            <AlertCircle size={18} />
            Students can view and register for events, but cannot create events.
          </div>
        )}

        <form className="form-stack" onSubmit={onCreateEvent}>
          <label>
            Event Title
            <input
              value={eventForm.title}
              onChange={(event) => onEventFormChange((form) => ({ ...form, title: event.target.value }))}
              placeholder="Example: Research Forum"
            />
          </label>
          <div className="form-row">
            <label>
              Category
              <select
                value={eventForm.category}
                onChange={(event) => onEventFormChange((form) => ({ ...form, category: event.target.value }))}
              >
                <option>Academic</option>
                <option>Technology</option>
                <option>Research</option>
                <option>Student Life</option>
              </select>
            </label>
            <label>
              Capacity
              <input
                value={eventForm.capacity}
                onChange={(event) => onEventFormChange((form) => ({ ...form, capacity: event.target.value }))}
                type="number"
                min="1"
              />
            </label>
          </div>
          <label>
            Date
            <input
              value={eventForm.date}
              onChange={(event) => onEventFormChange((form) => ({ ...form, date: event.target.value }))}
              type="date"
            />
          </label>
          <label>
            Location
            <input
              value={eventForm.location}
              onChange={(event) => onEventFormChange((form) => ({ ...form, location: event.target.value }))}
              placeholder="Room or venue"
            />
          </label>
          <button className="primary-button" type="submit">
            <CalendarPlus size={18} />
            Create Event
          </button>
        </form>
      </div>

      <div className="panel">
        <div className="panel-heading">
          <div>
            <p className="eyebrow">Management View</p>
            <h2>Event Capacity</h2>
          </div>
          <Users size={22} className="soft-icon" />
        </div>
        <div className="capacity-list">
          {events.map((event) => (
            <div className="capacity-row" key={event.id}>
              <span>{event.title}</span>
              <strong>
                {event.registered.length}/{event.capacity}
              </strong>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
}

function AuditView({ auditLog }) {
  return (
    <section className="audit-panel">
      <div className="section-heading">
        <div>
          <p className="eyebrow">Stewardship Evidence</p>
          <h2>Audit Trail</h2>
        </div>
        <ClipboardCheck size={22} className="soft-icon" />
      </div>
      <div className="audit-list">
        {auditLog.map((entry, index) => (
          <div className="audit-row" key={`${entry.action}-${index}`}>
            <span>{entry.actor}</span>
            <strong>{entry.action}</strong>
            <p>{entry.result}</p>
          </div>
        ))}
      </div>
    </section>
  );
}

function Notice({ notice }) {
  return (
    <section className={`notice ${notice.type}`} aria-live="polite">
      {notice.type === 'error' ? <AlertCircle size={20} /> : <CheckCircle2 size={20} />}
      <span>{notice.text}</span>
    </section>
  );
}

function SummaryCard({ icon, label, value }) {
  return (
    <article className="summary-card">
      <div className="summary-icon">{icon}</div>
      <span>{label}</span>
      <strong>{value}</strong>
    </article>
  );
}

createRoot(document.getElementById('root')).render(<App />);
